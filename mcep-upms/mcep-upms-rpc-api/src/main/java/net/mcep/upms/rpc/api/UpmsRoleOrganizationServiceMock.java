package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsRoleOrganizationMapper;
import net.mcep.upms.dao.model.UpmsRoleOrganization;
import net.mcep.upms.dao.model.UpmsRoleOrganizationExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsRoleOrganizationService接口
* Created by shuzheng on 2018/1/19.
*/
public class UpmsRoleOrganizationServiceMock extends BaseServiceMock<UpmsRoleOrganizationMapper, UpmsRoleOrganization, UpmsRoleOrganizationExample> implements UpmsRoleOrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsRoleOrganizationServiceMock.class);
    @Override
    public int organization(String[] organizationIds, int id) {
        LOGGER.info("UpmsRoleOrganizationServiceMock => organization");
        return 0;
    }


}
