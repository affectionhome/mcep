package net.mcep.cms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.mapper.CmsSystemMapper;
import net.mcep.cms.dao.model.CmsSystem;
import net.mcep.cms.dao.model.CmsSystemExample;

/**
* 降级实现CmsSystemService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsSystemServiceMock extends BaseServiceMock<CmsSystemMapper, CmsSystem, CmsSystemExample> implements CmsSystemService {

}
