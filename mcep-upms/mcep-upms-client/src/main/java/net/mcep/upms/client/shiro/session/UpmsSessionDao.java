package net.mcep.upms.client.shiro.session;

import net.mcep.common.util.PropertiesFileUtil;
import net.mcep.common.util.RedisUtil;
import net.mcep.upms.client.util.SerializableUtil;
import net.mcep.upms.common.constant.UpmsConstant;
import org.apache.commons.lang.ObjectUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 基于redis的sessionDao，缓存共享session
 * Created by shumcep on 2017/2/23.
 */
public class UpmsSessionDao extends CachingSessionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsSessionDao.class);
    // 会话key
    private final static String MCEP_UPMS_SHIRO_SESSION_ID = "mcep-upms-shiro-session-id";
    // 全局会话key
    private final static String MCEP_UPMS_SERVER_SESSION_ID = "mcep-upms-server-session-id";
    // 全局会话列表key
    private final static String MCEP_UPMS_SERVER_SESSION_IDS = "mcep-upms-server-session-ids";
    // code key
    private final static String MCEP_UPMS_SERVER_CODE = "mcep-upms-server-code";
    // 局部会话key
    private final static String MCEP_UPMS_CLIENT_SESSION_ID = "mcep-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String MCEP_UPMS_CLIENT_SESSION_IDS = "mcep-upms-client-session-ids";

    private static Date CUR_DATE = new Date();

    private static int INTERVALS = 60 *1000;//时间间隔 60 秒

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RedisUtil.set(MCEP_UPMS_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        LOGGER.debug("doCreate >>>>> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = RedisUtil.get(MCEP_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        LOGGER.debug("doReadSession >>>>> sessionId={}", sessionId);
        return SerializableUtil.deserialize(session);
    }

    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        // 更新session的最后一次访问时间
        UpmsSession upmsSession = (UpmsSession) session;
        UpmsSession cacheUpmsSession = (UpmsSession) doReadSession(session.getId());
        if (null != cacheUpmsSession) {
            upmsSession.setStatus(cacheUpmsSession.getStatus());
            upmsSession.setAttribute("FORCE_LOGOUT", cacheUpmsSession.getAttribute("FORCE_LOGOUT"));
        }
        RedisUtil.set(MCEP_UPMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        // 更新MCEP_UPMS_SERVER_SESSION_ID、MCEP_UPMS_SERVER_CODE过期时间 TODO
        /*****************************方法一：直接更新upms_session_id的会话（前提是几个系统的会话缓存都放在一个redis中）************************************/
        String upmsSessoinId = upmsSession.getUpmsSessionId();
        if(upmsSessoinId!=null&&upmsSessoinId.length()>0){
            String sessionStr = RedisUtil.get(MCEP_UPMS_SHIRO_SESSION_ID + "_" + upmsSessoinId);
            if(sessionStr!=null){
                Session sessionObj = SerializableUtil.deserialize(sessionStr);
                UpmsSession upmsSessionObj = (UpmsSession)sessionObj;
                Date lastAccessTime = upmsSessionObj.getLastAccessTime();
                Date now = new Date();
                /**********如果上次访问时间 + 间隔时间 < 当前时间，就更新upms中的会话缓存*************/
                if(lastAccessTime.getTime() + INTERVALS < now.getTime()){
                    upmsSessionObj.setLastAccessTime(now);
                    RedisUtil.set(MCEP_UPMS_SHIRO_SESSION_ID + "_" + upmsSessoinId, SerializableUtil.serialize(upmsSessionObj), (int)upmsSessionObj.getTimeout()/1000);//更新MCEP_UPMS_SHIRO_SESSION_ID
                    String code = RedisUtil.get(MCEP_UPMS_SERVER_SESSION_ID + "_" + upmsSessoinId);
                    RedisUtil.set(MCEP_UPMS_SERVER_SESSION_ID + "_" + upmsSessoinId, code, (int)upmsSessionObj.getTimeout()/1000);//更新MCEP_UPMS_SERVER_SESSION_ID
                    RedisUtil.set(MCEP_UPMS_SERVER_CODE + "_" + code, code, (int)upmsSessionObj.getTimeout()/1000 );//更新 MCEP_UPMS_SERVER_CODE
                }
            }
        }

        /******************************************************************************************/

        /*****************************方法一：通知UPMS去更新upms_session_id的会话************************************/
        /*String upmsType = PropertiesFileUtil.getInstance("mcep-upms-client").get("mcep.upms.type");
        if ("client".equals(upmsType)) {
            Date newTime = new Date();
            if(newTime.getTime() - INTERVALS > CUR_DATE.getTime()){
                StringBuffer ssoServerUrl = new StringBuffer(PropertiesFileUtil.getInstance("mcep-upms-client").get("mcep.upms.sso.server.url"));
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(ssoServerUrl.toString() + "/sso/session");

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                String upmsSessoinId = upmsSession.getUpmsSessionId();
                nameValuePairs.add(new BasicNameValuePair("upms_session_id", upmsSessoinId));
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse = httpclient.execute(httpPost);
                    CUR_DATE = newTime;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }*/

        /******************************************************************************************/


        LOGGER.debug("doUpdate >>>>> sessionId={}", session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        String upmsType = ObjectUtils.toString(session.getAttribute(UpmsConstant.UPMS_TYPE));
        if ("client".equals(upmsType)) {
            // 删除局部会话和同一code注册的局部会话
            String code = RedisUtil.get(MCEP_UPMS_CLIENT_SESSION_ID + "_" + sessionId);
            Jedis jedis = RedisUtil.getJedis();
            jedis.del(MCEP_UPMS_CLIENT_SESSION_ID + "_" + sessionId);
            jedis.srem(MCEP_UPMS_CLIENT_SESSION_IDS + "_" + code, sessionId);
            jedis.close();
        }
        if ("server".equals(upmsType)) {
            // 当前全局会话code
            String code = RedisUtil.get(MCEP_UPMS_SERVER_SESSION_ID + "_" + sessionId);
            // 清除全局会话
            RedisUtil.remove(MCEP_UPMS_SERVER_SESSION_ID + "_" + sessionId);
            // 清除code校验值
            RedisUtil.remove(MCEP_UPMS_SERVER_CODE + "_" + code);
            // 清除所有局部会话
            Jedis jedis = RedisUtil.getJedis();
            Set<String> clientSessionIds = jedis.smembers(MCEP_UPMS_CLIENT_SESSION_IDS + "_" + code);
            for (String clientSessionId : clientSessionIds) {
                jedis.del(MCEP_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId);
                jedis.srem(MCEP_UPMS_CLIENT_SESSION_IDS + "_" + code, clientSessionId);
                jedis.del(MCEP_UPMS_SHIRO_SESSION_ID + "_" + clientSessionId);

            }
            LOGGER.debug("当前code={}，对应的注册系统个数：{}个", code, jedis.scard(MCEP_UPMS_CLIENT_SESSION_IDS + "_" + code));
            jedis.close();
            // 维护会话id列表，提供会话分页管理
            RedisUtil.lrem(MCEP_UPMS_SERVER_SESSION_IDS, 1, sessionId);
        }
        // 删除session
        RedisUtil.remove(MCEP_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        LOGGER.debug("doUpdate >>>>> sessionId={}", sessionId);
    }

    /**
     * 获取会话列表
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map sessions = new HashMap();
        Jedis jedis = RedisUtil.getJedis();
        // 获取在线会话总数
        long total = jedis.llen(MCEP_UPMS_SERVER_SESSION_IDS);
        // 获取当前页会话详情
        List<String> ids = jedis.lrange(MCEP_UPMS_SERVER_SESSION_IDS, offset, (offset + limit - 1));
        List<Session> rows = new ArrayList<>();
        for (String id : ids) {
            String session = RedisUtil.get(MCEP_UPMS_SHIRO_SESSION_ID + "_" + id);
            // 过滤redis过期session
            if (null == session) {
                RedisUtil.lrem(MCEP_UPMS_SERVER_SESSION_IDS, 1, id);
                total = total - 1;
                continue;
            }
             rows.add(SerializableUtil.deserialize(session));
        }
        jedis.close();
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            String session = RedisUtil.get(MCEP_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
            UpmsSession upmsSession = (UpmsSession) SerializableUtil.deserialize(session);
            upmsSession.setStatus(UpmsSession.OnlineStatus.force_logout);
            upmsSession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            RedisUtil.set(MCEP_UPMS_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(upmsSession), (int) upmsSession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状态
     *
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, UpmsSession.OnlineStatus onlineStatus) {
        UpmsSession session = (UpmsSession) doReadSession(sessionId);
        if (null == session) {
            return;
        }
        session.setStatus(onlineStatus);
        RedisUtil.set(MCEP_UPMS_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
    }

}
