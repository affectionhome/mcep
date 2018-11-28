package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsResourceMapper;
import net.mcep.upms.dao.model.UpmsResource;
import net.mcep.upms.dao.model.UpmsResourceExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsResourceService接口
* Created by shuzheng on 2018/1/18.
*/
public class UpmsResourceServiceMock extends BaseServiceMock<UpmsResourceMapper, UpmsResource, UpmsResourceExample> implements UpmsResourceService {



    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceServiceMock.class);

    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        LOGGER.info("UpmsResourceServiceMock => getTreeByRoleId");
        return null;
    }

    @Override
    public JSONArray getTreeByJobsId(Integer jobsId) {
        LOGGER.info("UpmsResourceServiceMock => getTreeByJobsId");
        return null;
    }

    @Override
    public JSONArray getTreeByUserId(Integer userId) {
        LOGGER.info("UpmsResourceServiceMock => getTreeByUserId");
        return null;
    }

    @Override
    public String getResourceByUserId(Integer userId, String type) {
        return null;
    }


}
