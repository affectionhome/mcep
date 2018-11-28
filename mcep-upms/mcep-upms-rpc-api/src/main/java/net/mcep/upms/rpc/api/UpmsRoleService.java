package net.mcep.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import net.mcep.common.base.BaseService;
import net.mcep.upms.dao.model.UpmsRole;
import net.mcep.upms.dao.model.UpmsRoleExample;
import net.mcep.upms.dao.model.UpmsUserRole;

import java.util.List;

/**
* UpmsRoleService接口
* Created by shuzheng on 2017/3/20.
*/
public interface UpmsRoleService extends BaseService<UpmsRole, UpmsRoleExample> {

    JSONArray getTreeByResourceId(Integer resourceId);


    int updateAndCommit(int id, List<UpmsUserRole> userRoles);
}