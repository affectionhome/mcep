package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseServiceMock;
import net.mcep.upms.dao.mapper.UpmsCodeClassMapper;
import net.mcep.upms.dao.model.UpmsCodeClass;
import net.mcep.upms.dao.model.UpmsCodeClassExample;

/**
* 降级实现UpmsCodeClassService接口
* Created by shuzheng on 2018/1/26.
*/
public class UpmsCodeClassServiceMock extends BaseServiceMock<UpmsCodeClassMapper, UpmsCodeClass, UpmsCodeClassExample> implements UpmsCodeClassService {

    @Override
    public int createReturnKey(UpmsCodeClass upmsCodeClass) {
        return 0;
    }

    @Override
    public int updateReturnKey(UpmsCodeClass upmsCodeClass) {
        return 0;
    }


}
