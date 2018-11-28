package net.mcep.cms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.mapper.CmsPageMapper;
import net.mcep.cms.dao.model.CmsPage;
import net.mcep.cms.dao.model.CmsPageExample;

/**
* 降级实现CmsPageService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsPageServiceMock extends BaseServiceMock<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

}
