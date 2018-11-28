package net.mcep.cms.rpc.service.impl;

import net.mcep.cms.dao.mapper.CmsTagMapper;
import net.mcep.cms.dao.model.CmsTag;
import net.mcep.cms.dao.model.CmsTagExample;
import net.mcep.cms.rpc.api.CmsTagService;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsTagService实现
* Created by shuzheng on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsTagServiceImpl extends BaseServiceImpl<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsTagServiceImpl.class);

    @Autowired
    CmsTagMapper cmsTagMapper;

}