package net.mcep.cms.rpc.api;

import net.mcep.cms.dao.model.CmsComment;
import net.mcep.cms.dao.model.CmsCommentExample;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.mapper.CmsCommentMapper;

/**
* 降级实现CmsCommentService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsCommentServiceMock extends BaseServiceMock<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

}
