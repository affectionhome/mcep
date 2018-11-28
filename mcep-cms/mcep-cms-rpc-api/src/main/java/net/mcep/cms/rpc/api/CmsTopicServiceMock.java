package net.mcep.cms.rpc.api;

import net.mcep.cms.dao.mapper.CmsTopicMapper;
import net.mcep.cms.dao.model.CmsTopic;
import net.mcep.cms.dao.model.CmsTopicExample;
import net.mcep.common.base.BaseServiceMock;

/**
* 降级实现CmsTopicService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsTopicServiceMock extends BaseServiceMock<CmsTopicMapper, CmsTopic, CmsTopicExample> implements CmsTopicService {

}
