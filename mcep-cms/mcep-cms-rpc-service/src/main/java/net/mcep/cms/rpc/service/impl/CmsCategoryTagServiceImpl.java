package net.mcep.cms.rpc.service.impl;

import net.mcep.cms.dao.model.CmsCategoryTagExample;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.cms.dao.mapper.CmsCategoryTagMapper;
import net.mcep.cms.dao.model.CmsCategoryTag;
import net.mcep.cms.rpc.api.CmsCategoryTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsCategoryTagService实现
* Created by shuzheng on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsCategoryTagServiceImpl extends BaseServiceImpl<CmsCategoryTagMapper, CmsCategoryTag, CmsCategoryTagExample> implements CmsCategoryTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsCategoryTagServiceImpl.class);

    @Autowired
    CmsCategoryTagMapper cmsCategoryTagMapper;

}