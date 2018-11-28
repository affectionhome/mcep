package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsResourceUser;
import net.mcep.upms.dao.model.UpmsResourceUserExample;

/**
* UpmsResourceUserService接口
* Created by shuzheng on 2018/1/26.
*/
public interface UpmsResourceUserService extends BaseService<UpmsResourceUser, UpmsResourceUserExample> {

    /**
     * 资源用户
     * @param datas 权限数据
     * @param id 资源id
     * @return
     */
    int resourceUser(JSONArray datas, int id);


    /**
     * 用户资源权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int resourceUser1(JSONArray datas, int id);


}