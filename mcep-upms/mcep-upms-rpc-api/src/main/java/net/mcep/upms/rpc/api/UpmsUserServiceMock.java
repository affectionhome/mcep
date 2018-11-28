package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsUserMapper;
import net.mcep.upms.dao.model.UpmsUser;
import net.mcep.upms.dao.model.UpmsUserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsUserService接口
* Created by shuzheng on 2017/3/20.
*/
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceMock.class);

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        return null;
    }

    @Override
    public JSONArray getTreeByResourceId(Integer resourceId) {
        LOGGER.info("UpmsUserServiceMock => getTreeByResourceId");
        return null;
    }


}
