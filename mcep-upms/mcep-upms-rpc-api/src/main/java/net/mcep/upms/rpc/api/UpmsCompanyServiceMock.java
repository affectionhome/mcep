package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsCompanyMapper;
import net.mcep.upms.dao.model.UpmsCompany;
import net.mcep.upms.dao.model.UpmsCompanyExample;

/**
* 降级实现UpmsCompanyService接口
* Created by shuzheng on 2017/11/28.
*/
public class UpmsCompanyServiceMock extends BaseServiceMock<UpmsCompanyMapper, UpmsCompany, UpmsCompanyExample> implements UpmsCompanyService {

    @Override
    public Integer getNInsertIdByName(String companyName) {
        return null;
    }

    @Override
    public Integer saveOrUpdataCompany(UpmsCompany upmsCompany) {
        return null;
    }
}
