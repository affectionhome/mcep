package net.mcep.upms.rpc.api;

import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsCodeClass;
import net.mcep.upms.dao.model.UpmsCodeClassExample;

/**
* UpmsCodeClassService接口
* Created by shuzheng on 2018/1/26.
*/
public interface UpmsCodeClassService extends BaseService<UpmsCodeClass, UpmsCodeClassExample> {


    public int createReturnKey(UpmsCodeClass upmsCodeClass);

    public int updateReturnKey(UpmsCodeClass upmsCodeClass);

}