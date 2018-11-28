package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsCompany;
import net.mcep.upms.dao.model.UpmsCompanyExample;

/**
* UpmsCompanyService接口
* Created by shuzheng on 2017/11/28.
*/
public interface UpmsCompanyService extends BaseService<UpmsCompany, UpmsCompanyExample> {

    Integer getNInsertIdByName(String companyName);

    Integer saveOrUpdataCompany(UpmsCompany upmsCompany);
}