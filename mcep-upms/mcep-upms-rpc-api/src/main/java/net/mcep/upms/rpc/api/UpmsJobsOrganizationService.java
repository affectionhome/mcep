package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsJobsOrganization;
import net.mcep.upms.dao.model.UpmsJobsOrganizationExample;

/**
* UpmsJobsOrganizationService接口
* Created by shuzheng on 2018/1/27.
*/
public interface UpmsJobsOrganizationService extends BaseService<UpmsJobsOrganization, UpmsJobsOrganizationExample> {


    /**
     * 岗位组织
     * @param organizationIds 组织ids
     * @param id 岗位id
     * @return
     */
    int organizations(String[] organizationIds, int id);


}