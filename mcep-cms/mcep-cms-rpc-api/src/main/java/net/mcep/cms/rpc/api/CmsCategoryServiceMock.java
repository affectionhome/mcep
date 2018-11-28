package net.mcep.cms.rpc.api;

import net.mcep.cms.dao.mapper.CmsCategoryMapper;
import net.mcep.cms.dao.model.CmsCategoryExample;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.model.CmsCategory;

/**
* 降级实现CmsCategoryService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsCategoryServiceMock extends BaseServiceMock<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {

}
