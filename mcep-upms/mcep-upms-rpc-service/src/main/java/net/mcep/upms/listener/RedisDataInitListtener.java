package net.mcep.upms.listener;

import io.swagger.models.auth.In;
import net.mcep.common.util.RedisUtil;
import net.mcep.upms.dao.mapper.UpmsUserMapper;
import net.mcep.upms.dao.model.UpmsUser;
import net.mcep.upms.dao.model.UpmsUserExample;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * 方法名称 : net.mcep.upms.listener.RedisDataInitListtener
 * 作   者 : admin
 * 创建时间 : 2018/3/29 19:40
 * 方法描述 : 初始化redis缓存数据
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */

public class RedisDataInitListtener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            UpmsUserMapper upmsUserMapper = (UpmsUserMapper)contextRefreshedEvent.getApplicationContext().getBean(UpmsUserMapper.class);

            UpmsUserExample example = new UpmsUserExample();
            List<UpmsUser> list = upmsUserMapper.selectByExample(example);
            for(UpmsUser user : list){
                Integer userid = user.getUserId();
                String realname = user.getRealname();
                RedisUtil.set("mcep-user-"+userid,realname);
            }
        }

    }
}