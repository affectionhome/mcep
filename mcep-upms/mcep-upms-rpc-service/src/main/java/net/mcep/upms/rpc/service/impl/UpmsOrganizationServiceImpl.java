package net.mcep.upms.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsOrganizationMapper;
import net.mcep.upms.dao.model.UpmsOrganization;
import net.mcep.upms.dao.model.UpmsOrganizationExample;
import net.mcep.upms.rpc.api.UpmsOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsOrganizationService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsOrganizationServiceImpl.class);

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

}