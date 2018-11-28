package net.mcep.upms.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsCodeMapper;
import net.mcep.upms.dao.model.UpmsCode;
import net.mcep.upms.dao.model.UpmsCodeExample;
import net.mcep.upms.rpc.api.UpmsCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsCodeService实现
* Created by shuzheng on 2018/1/26.
*/
@Service
@Transactional
@BaseService
public class UpmsCodeServiceImpl extends BaseServiceImpl<UpmsCodeMapper, UpmsCode, UpmsCodeExample> implements UpmsCodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsCodeServiceImpl.class);

    @Autowired
    UpmsCodeMapper upmsCodeMapper;

}