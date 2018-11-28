package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsUserJobsMapper;
import net.mcep.upms.dao.model.UpmsUserJobs;
import net.mcep.upms.dao.model.UpmsUserJobsExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsUserJobsService接口
* Created by shuzheng on 2018/1/26.
*/
public class UpmsUserJobsServiceMock extends BaseServiceMock<UpmsUserJobsMapper, UpmsUserJobs, UpmsUserJobsExample> implements UpmsUserJobsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserJobsServiceMock.class);

    @Override
    public int jobs(String[] jobsIds, int id) {
        LOGGER.info("UpmsUserJobsServiceMock => jobs");
        return 0;
    }

    @Override
    public UpmsUserJobs createUserJobs(UpmsUserJobs upmsUserJobs) {
        return null;
    }

}
