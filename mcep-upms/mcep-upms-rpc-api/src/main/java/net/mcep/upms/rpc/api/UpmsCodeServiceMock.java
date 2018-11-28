package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsCodeMapper;
import net.mcep.upms.dao.model.UpmsCode;
import net.mcep.upms.dao.model.UpmsCodeExample;

/**
* 降级实现UpmsCodeService接口
* Created by shuzheng on 2018/1/26.
*/
public class UpmsCodeServiceMock extends BaseServiceMock<UpmsCodeMapper, UpmsCode, UpmsCodeExample> implements UpmsCodeService {

}
