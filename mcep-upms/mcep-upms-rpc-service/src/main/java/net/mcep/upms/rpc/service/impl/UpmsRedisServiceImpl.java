package net.mcep.upms.rpc.service.impl;

import net.mcep.common.util.RedisUtil;
import net.mcep.upms.dao.mapper.UpmsUserMapper;
import net.mcep.upms.dao.model.UpmsUser;
import net.mcep.upms.dao.model.UpmsUserExample;
import net.mcep.upms.rpc.api.UpmsRedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 方法名称 : net.mcep.upms.rpc.service.impl.UpmsRedisServiceImpl
 * 作   者 : Administrator
 * 创建时间 : 2018/8/17 10:17
 * 方法描述 : redis缓存管理
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */

public class UpmsRedisServiceImpl implements UpmsRedisService {

    @Autowired
    UpmsUserMapper upmsUserMapper;

    @Override
    public String initUserToRedis() {
        UpmsUserExample example = new UpmsUserExample();
        List<UpmsUser> list = upmsUserMapper.selectByExample(example);
        for(UpmsUser user : list){
            Integer userid = user.getUserId();
            String realname = user.getRealname();
            RedisUtil.set("mcep-user-"+userid,realname);
        }
        return "1";
    }
}
