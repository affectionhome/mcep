package net.mcep.cms.rpc.service.impl;

import net.mcep.cms.dao.mapper.CmsArticleCategoryMapper;
import net.mcep.cms.dao.model.CmsArticleCategory;
import net.mcep.cms.dao.model.CmsArticleCategoryExample;
import net.mcep.cms.rpc.api.CmsArticleCategoryService;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsArticleCategoryService实现
* Created by shuzheng on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsArticleCategoryServiceImpl extends BaseServiceImpl<CmsArticleCategoryMapper, CmsArticleCategory, CmsArticleCategoryExample> implements CmsArticleCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsArticleCategoryServiceImpl.class);

    @Autowired
    CmsArticleCategoryMapper cmsArticleCategoryMapper;

}