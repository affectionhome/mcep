package net.mcep.cms.rpc.api;

import net.mcep.cms.dao.mapper.CmsSettingMapper;
import net.mcep.cms.dao.model.CmsSetting;
import net.mcep.common.base.BaseServiceMock;
import net.mcep.cms.dao.model.CmsSettingExample;

/**
* 降级实现CmsSettingService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsSettingServiceMock extends BaseServiceMock<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

}
