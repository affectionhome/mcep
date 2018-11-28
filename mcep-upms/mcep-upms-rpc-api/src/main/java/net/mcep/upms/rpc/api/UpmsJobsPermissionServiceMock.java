package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsJobsPermissionMapper;
import net.mcep.upms.dao.model.UpmsJobsPermission;
import net.mcep.upms.dao.model.UpmsJobsPermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsJobsPermissionService接口
* Created by shuzheng on 2018/4/13.
*/
public class UpmsJobsPermissionServiceMock extends BaseServiceMock<UpmsJobsPermissionMapper, UpmsJobsPermission, UpmsJobsPermissionExample> implements UpmsJobsPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsJobsPermissionServiceMock.class);

    @Override
    public int jobsPermission(JSONArray datas, int id) {
        LOGGER.info("UpmsJobsPermissionServiceMock => jobsPermission");
        return 0;
    }


}
