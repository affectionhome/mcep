package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsRoleOrganization;
import net.mcep.upms.dao.model.UpmsRoleOrganizationExample;

/**
* UpmsRoleOrganizationService接口
* Created by shuzheng on 2018/1/19.
*/
public interface UpmsRoleOrganizationService extends BaseService<UpmsRoleOrganization, UpmsRoleOrganizationExample> {

    /**
     * 角色组织
     * @param organizationIds 组织ids
     * @param id 角色id
     * @return
     */
    int organization(String[] organizationIds, int id);


}