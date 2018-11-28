package net.mcep.upms.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsCompanyMapper;
import net.mcep.upms.dao.model.UpmsCompany;
import net.mcep.upms.dao.model.UpmsCompanyExample;
import net.mcep.upms.rpc.api.UpmsCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UpmsCompanyService实现
* Created by shuzheng on 2017/11/28.
*/
@Service
@Transactional
@BaseService
public class UpmsCompanyServiceImpl extends BaseServiceImpl<UpmsCompanyMapper, UpmsCompany, UpmsCompanyExample> implements UpmsCompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsCompanyServiceImpl.class);

    @Autowired
    UpmsCompanyMapper upmsCompanyMapper;

    @Override
    public Integer getNInsertIdByName(String companyName) {
        Integer id = null;
        UpmsCompanyExample upmsCompanyExample = new UpmsCompanyExample();
        upmsCompanyExample.or().andCompanyNameEqualTo(companyName);

        List<UpmsCompany> companys = upmsCompanyMapper.selectByExampleWithBLOBs(upmsCompanyExample);
        if (companys.size() == 0){
            UpmsCompany company = new UpmsCompany();
            company.setCompanyName(companyName);
            upmsCompanyMapper.insertSelective(company);
            companys = upmsCompanyMapper.selectByExampleWithBLOBs(upmsCompanyExample);
        }
        id = companys.get(0).getCompanyId();
        return id;
    }

    @Override
    public Integer saveOrUpdataCompany(UpmsCompany upmsCompany) {
        Integer state = 0;
        Integer id = upmsCompany.getCompanyId();
        //重名查询
        UpmsCompanyExample upmsCompanyExample = new UpmsCompanyExample();
        upmsCompanyExample.or().andCompanyNameEqualTo(upmsCompany.getCompanyName());
        List<UpmsCompany> upmsCompanies = upmsCompanyMapper.selectByExample(upmsCompanyExample);
        if (id == null){//新增
            if (upmsCompanies.size()>0){//有重名
                state = 1;
            }else {
                upmsCompanyMapper.insertSelective(upmsCompany);
            }
        }else{//修改
            if (upmsCompanies.size() == 1 && !upmsCompany.getCompanyName().equals(upmsCompanies.get(0).getCompanyName())){//修改后与其他重名
                state = 1;
            }else {
                upmsCompanyMapper.updateByPrimaryKey(upmsCompany);
            }
        }
        return state;
    }
}