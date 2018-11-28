package net.mcep.cms.rpc.service.impl;

import net.mcep.cms.dao.mapper.CmsCommentMapper;
import net.mcep.cms.dao.model.CmsComment;
import net.mcep.cms.dao.model.CmsCommentExample;
import net.mcep.cms.rpc.api.CmsCommentService;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsCommentService实现
* Created by shuzheng on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsCommentServiceImpl.class);

    @Autowired
    CmsCommentMapper cmsCommentMapper;

}