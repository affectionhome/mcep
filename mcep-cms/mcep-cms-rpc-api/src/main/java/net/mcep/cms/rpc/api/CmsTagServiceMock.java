package net.mcep.cms.rpc.api;

import net.mcep.cms.dao.mapper.CmsTagMapper;
import net.mcep.cms.dao.model.CmsTagExample;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.model.CmsTag;

/**
* 降级实现CmsTagService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsTagServiceMock extends BaseServiceMock<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

}
