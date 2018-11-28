package net.mcep.cms.rpc.service.impl;

import net.mcep.cms.dao.mapper.CmsMenuMapper;
import net.mcep.cms.dao.model.CmsMenu;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.cms.dao.model.CmsMenuExample;
import net.mcep.cms.rpc.api.CmsMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsMenuService实现
* Created by shuzheng on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsMenuServiceImpl extends BaseServiceImpl<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsMenuServiceImpl.class);

    @Autowired
    CmsMenuMapper cmsMenuMapper;

}