package net.mcep.cms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.mapper.CmsArticleTagMapper;
import net.mcep.cms.dao.model.CmsArticleTag;
import net.mcep.cms.dao.model.CmsArticleTagExample;

/**
* 降级实现CmsArticleTagService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsArticleTagServiceMock extends BaseServiceMock<CmsArticleTagMapper, CmsArticleTag, CmsArticleTagExample> implements CmsArticleTagService {

}
