package net.mcep.upms.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsDataPermissionMapper;
import net.mcep.upms.dao.model.UpmsDataPermission;
import net.mcep.upms.dao.model.UpmsDataPermissionExample;
import net.mcep.upms.rpc.api.UpmsDataPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsDataPermissionService实现
* Created by shuzheng on 2018/1/21.
*/
@Service
@Transactional
@BaseService
public class UpmsDataPermissionServiceImpl extends BaseServiceImpl<UpmsDataPermissionMapper, UpmsDataPermission, UpmsDataPermissionExample> implements UpmsDataPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsDataPermissionServiceImpl.class);

    @Autowired
    UpmsDataPermissionMapper upmsDataPermissionMapper;

}