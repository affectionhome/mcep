package net.mcep.cms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.mapper.CmsMenuMapper;
import net.mcep.cms.dao.model.CmsMenu;
import net.mcep.cms.dao.model.CmsMenuExample;

/**
* 降级实现CmsMenuService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsMenuServiceMock extends BaseServiceMock<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

}
