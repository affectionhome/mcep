package net.mcep.cms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.mapper.CmsCategoryTagMapper;
import net.mcep.cms.dao.model.CmsCategoryTag;
import net.mcep.cms.dao.model.CmsCategoryTagExample;

/**
* 降级实现CmsCategoryTagService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsCategoryTagServiceMock extends BaseServiceMock<CmsCategoryTagMapper, CmsCategoryTag, CmsCategoryTagExample> implements CmsCategoryTagService {

}
