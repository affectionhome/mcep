package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsJobsPermission;
import net.mcep.upms.dao.model.UpmsJobsPermissionExample;

/**
* UpmsJobsPermissionService接口
* Created by shuzheng on 2018/4/13.
*/
public interface UpmsJobsPermissionService extends BaseService<UpmsJobsPermission, UpmsJobsPermissionExample> {


    /**
     * 岗位权限
     * @param datas 权限数据
     * @param id 岗位id
     * @return
     */
    int jobsPermission(JSONArray datas, int id);


}