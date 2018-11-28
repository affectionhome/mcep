package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsResourceRole;
import net.mcep.upms.dao.model.UpmsResourceRoleExample;

/**
* UpmsResourceRoleService接口
* Created by shuzheng on 2018/1/28.
*/
public interface UpmsResourceRoleService extends BaseService<UpmsResourceRole, UpmsResourceRoleExample> {


    /**
     * 资源角色
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int resourceRole(JSONArray datas, int id);

    /**
     * 资源角色
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int resourceRole1(JSONArray datas, int id);


}