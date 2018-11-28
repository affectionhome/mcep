package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsResource;
import net.mcep.upms.dao.model.UpmsResourceExample;

/**
* UpmsResourceService接口
* Created by shuzheng on 2018/1/18.
*/
public interface UpmsResourceService extends BaseService<UpmsResource, UpmsResourceExample> {


    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByJobsId(Integer jobsId);

    JSONArray getTreeByUserId(Integer userId);

    String getResourceByUserId(Integer userId,String type);

}