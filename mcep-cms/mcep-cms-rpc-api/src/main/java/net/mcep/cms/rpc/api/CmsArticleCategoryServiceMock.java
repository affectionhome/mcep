package net.mcep.cms.rpc.api;

import net.mcep.cms.dao.model.CmsArticleCategory;
import net.mcep.cms.dao.model.CmsArticleCategoryExample;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.mapper.CmsArticleCategoryMapper;

/**
* 降级实现CmsArticleCategoryService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsArticleCategoryServiceMock extends BaseServiceMock<CmsArticleCategoryMapper, CmsArticleCategory, CmsArticleCategoryExample> implements CmsArticleCategoryService {

}
