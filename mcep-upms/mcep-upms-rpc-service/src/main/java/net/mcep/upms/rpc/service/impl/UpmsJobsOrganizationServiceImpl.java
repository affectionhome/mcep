package net.mcep.upms.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsJobsOrganizationMapper;
import net.mcep.upms.dao.model.UpmsJobsOrganization;
import net.mcep.upms.dao.model.UpmsJobsOrganizationExample;
import net.mcep.upms.rpc.api.UpmsJobsOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsJobsOrganizationService实现
* Created by shuzheng on 2018/1/27.
*/
@Service
@Transactional
@BaseService
public class UpmsJobsOrganizationServiceImpl extends BaseServiceImpl<UpmsJobsOrganizationMapper, UpmsJobsOrganization, UpmsJobsOrganizationExample> implements UpmsJobsOrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsJobsOrganizationServiceImpl.class);

    @Autowired
    UpmsJobsOrganizationMapper upmsJobsOrganizationMapper;


    @Override
    public int organizations(String[] organizationIds, int id) {
        int result = 0;
        // 删除旧记录
        UpmsJobsOrganizationExample upmsJobsOrganizationExample = new UpmsJobsOrganizationExample();
        upmsJobsOrganizationExample.createCriteria()
                .andJobsIdEqualTo(id);
        upmsJobsOrganizationMapper.deleteByExample(upmsJobsOrganizationExample);
        // 增加新记录
        if (null != organizationIds) {
            for (String organizationId : organizationIds) {
                if (StringUtils.isBlank(organizationId)) {
                    continue;
                }
                UpmsJobsOrganization upmsJobsOrganization = new UpmsJobsOrganization();
                upmsJobsOrganization.setJobsId(id);
                upmsJobsOrganization.setOrganizationId(NumberUtils.toInt(organizationId));
                result = upmsJobsOrganizationMapper.insertSelective(upmsJobsOrganization);
            }
        }
        return result;
    }


}