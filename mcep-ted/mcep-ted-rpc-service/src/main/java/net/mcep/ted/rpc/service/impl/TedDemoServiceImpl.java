package net.mcep.ted.rpc.service.impl;

import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.ted.dao.mapper.TedDemoMapper;
import net.mcep.ted.dao.model.TedDemo;
import net.mcep.ted.dao.model.TedDemoExample;
import net.mcep.ted.rpc.api.TedDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* TedDemoService实现
*/
@Service
@Transactional
@BaseService
public class TedDemoServiceImpl extends BaseServiceImpl<TedDemoMapper, TedDemo, TedDemoExample> implements TedDemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TedDemoServiceImpl.class);

    @Autowired
    TedDemoMapper tedDemoMapper;

    /** 简单的单表增改查接口已经在 BaseServiceImpl 中处理*/

    /** 在这里实现 TedDemoService 中自己新定义的接口 ，这里是真实处理相关业务逻辑的 */

}