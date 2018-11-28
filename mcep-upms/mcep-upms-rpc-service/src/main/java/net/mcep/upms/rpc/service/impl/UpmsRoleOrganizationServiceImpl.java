package net.mcep.upms.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsRoleOrganizationMapper;
import net.mcep.upms.dao.model.UpmsRoleOrganization;
import net.mcep.upms.dao.model.UpmsRoleOrganizationExample;
import net.mcep.upms.dao.model.UpmsUserOrganization;
import net.mcep.upms.dao.model.UpmsUserOrganizationExample;
import net.mcep.upms.rpc.api.UpmsRoleOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsRoleOrganizationService实现
* Created by shuzheng on 2018/1/19.
*/
@Service
@Transactional
@BaseService
public class UpmsRoleOrganizationServiceImpl extends BaseServiceImpl<UpmsRoleOrganizationMapper, UpmsRoleOrganization, UpmsRoleOrganizationExample> implements UpmsRoleOrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsRoleOrganizationServiceImpl.class);

    @Autowired
    UpmsRoleOrganizationMapper upmsRoleOrganizationMapper;

    @Override
    public int organization(String[] organizationIds, int id) {
        int result = 0;
        // 删除旧记录
        UpmsRoleOrganizationExample upmsRoleOrganizationExample = new UpmsRoleOrganizationExample();
        upmsRoleOrganizationExample.createCriteria()
                .andRoleIdEqualTo(id);
        upmsRoleOrganizationMapper.deleteByExample(upmsRoleOrganizationExample);
        // 增加新记录
        if (null != organizationIds) {
            for (String organizationId : organizationIds) {
                if (StringUtils.isBlank(organizationId)) {
                    continue;
                }
                UpmsRoleOrganization upmsRoleOrganization = new UpmsRoleOrganization();
                upmsRoleOrganization.setRoleId(id);
                upmsRoleOrganization.setOrganizationId(NumberUtils.toInt(organizationId));
                result = upmsRoleOrganizationMapper.insertSelective(upmsRoleOrganization);
            }
        }
        return result;
    }

}