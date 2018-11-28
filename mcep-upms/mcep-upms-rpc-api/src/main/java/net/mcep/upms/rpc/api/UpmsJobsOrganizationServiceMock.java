package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsJobsOrganizationMapper;
import net.mcep.upms.dao.model.UpmsJobsOrganization;
import net.mcep.upms.dao.model.UpmsJobsOrganizationExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsJobsOrganizationService接口
* Created by shuzheng on 2018/1/27.
*/
public class UpmsJobsOrganizationServiceMock extends BaseServiceMock<UpmsJobsOrganizationMapper, UpmsJobsOrganization, UpmsJobsOrganizationExample> implements UpmsJobsOrganizationService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsJobsOrganizationServiceMock.class);

    @Override
    public int organizations(String[] organizationIds, int id) {
        LOGGER.info("UpmsJobsOrganizationServiceMock => organization");
        return 0;
    }




}
