package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsPermissionMapper;
import net.mcep.upms.dao.mapper.UpmsSystemMapper;
import net.mcep.upms.dao.mapper.UpmsUserPermissionMapper;
import net.mcep.upms.dao.model.*;
import net.mcep.upms.rpc.api.UpmsApiService;
import net.mcep.upms.rpc.api.UpmsPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UpmsPermissionService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsPermissionServiceImpl extends BaseServiceImpl<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements UpmsPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsPermissionServiceImpl.class);

    @Autowired
    UpmsSystemMapper upmsSystemMapper;

    @Autowired
    UpmsPermissionMapper upmsPermissionMapper;

    @Autowired
    UpmsApiService upmsApiService;

    @Autowired
    UpmsUserPermissionMapper upmsUserPermissionMapper;

    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        // 角色已有权限
        List<UpmsRolePermission> rolePermissions = upmsApiService.selectUpmsRolePermisstionByUpmsRoleId(roleId);

        JSONArray systems = new JSONArray();
        // 系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
                UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);
                if (upmsPermissions.size() == 0) {
                    continue;
                }
                // 一级目录集合
                JSONArray folders1 = new JSONArray();
                for (UpmsPermission upmsPermission: upmsPermissions) {
                    if (upmsPermission.getPid().intValue() != 0 || upmsPermission.getType() != 1) {
                        continue;
                    }
                    JSONObject node1 = new JSONObject();//一级目录
                    node1.put("id", upmsPermission.getPermissionId());
                    node1.put("name", upmsPermission.getName());
                    node1.put("open", true);
                    for (UpmsRolePermission rolePermission : rolePermissions) {
                        if (rolePermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node1.put("checked", true);
                        }
                    }
                    node1.put("children", getSunByObjRole(node1,upmsPermissions,rolePermissions));
                    folders1.add(node1);
                }
                if (folders1.size() > 0) {
                    ((JSONObject) system).put("children", folders1);
                }
            }
        }
        return systems;
    }


    private JSONArray getSunByObjRole(JSONObject obj, List<UpmsPermission> upmsPermissions, List<UpmsRolePermission> rolePermissions){
        JSONArray array = new JSONArray();
        for (UpmsPermission upmsPermission: upmsPermissions) {
            if (upmsPermission.getPid().intValue() != obj.getIntValue("id") ) {
                continue;
            }
            JSONObject node = new JSONObject();
            node.put("id", upmsPermission.getPermissionId());
            node.put("name", upmsPermission.getName());
            node.put("open", true);
            for (UpmsRolePermission rolePermission : rolePermissions) {
                if (rolePermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                    node.put("checked", true);
                }
            }
            JSONArray suns = getSunByObjRole(node, upmsPermissions, rolePermissions);
            if(suns==null||suns.size()==0){
                //return node3;
            }else{
                node.put("children", suns);
            }

            array.add(node);
        }

        return array;

    }

    @Override
    public JSONArray getTreeByUserId(Integer usereId, Byte type) {
        // 用户权限
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(usereId)
                .andTypeEqualTo(type);
        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);

        JSONArray systems = new JSONArray();
        // 系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
                UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);
                if (upmsPermissions.size() == 0) {
                    continue;
                }
                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsPermission upmsPermission: upmsPermissions) {
                    if (upmsPermission.getPid().intValue() != 0 || upmsPermission.getType() != 1) {
                        continue;
                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getPermissionId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                        if (upmsUserPermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    node.put("children", getSunByObjUser(node,upmsPermissions,upmsUserPermissions));
                    folders.add(node);

                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

    private JSONArray getSunByObjUser(JSONObject obj, List<UpmsPermission> upmsPermissions, List<UpmsUserPermission> userPermissions){
        JSONArray array = new JSONArray();
        for (UpmsPermission upmsPermission: upmsPermissions) {
            if (upmsPermission.getPid().intValue() != obj.getIntValue("id") ) {
                continue;
            }
            JSONObject node = new JSONObject();
            node.put("id", upmsPermission.getPermissionId());
            node.put("name", upmsPermission.getName());
            node.put("open", true);
            for (UpmsUserPermission userPermission : userPermissions) {
                if (userPermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                    node.put("checked", true);
                }
            }
            JSONArray suns = getSunByObjUser(node, upmsPermissions, userPermissions);
            if(suns==null||suns.size()==0){
                //return node3;
            }else{
                node.put("children", suns);
            }

            array.add(node);
        }

        return array;

    }



    @Override
    public JSONArray getTreeByJobsId(Integer jobsId) {
        // 岗位已有权限
        List<UpmsJobsPermission> jobsPermissions = upmsApiService.selectUpmsJobsPermisstionByUpmsJobsId(jobsId);

        JSONArray systems = new JSONArray();
        // 系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        upmsSystemExample.setOrderByClause("orders asc");
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
                UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
                upmsPermissionExample.createCriteria()
                        .andStatusEqualTo((byte) 1)
                        .andSystemIdEqualTo(((JSONObject) system).getIntValue("id"));
                upmsPermissionExample.setOrderByClause("orders asc");
                List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectByExample(upmsPermissionExample);
                if (upmsPermissions.size() == 0) {
                    continue;
                }
                // 一级目录集合
                JSONArray folders1 = new JSONArray();
                for (UpmsPermission upmsPermission: upmsPermissions) {
                    if (upmsPermission.getPid().intValue() != 0 || upmsPermission.getType() != 1) {
                        continue;
                    }
                    JSONObject node1 = new JSONObject();//一级目录
                    node1.put("id", upmsPermission.getPermissionId());
                    node1.put("name", upmsPermission.getName());
                    node1.put("open", true);
                    for (UpmsJobsPermission jobsPermission : jobsPermissions) {
                        if (jobsPermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node1.put("checked", true);
                        }
                    }
                    node1.put("children", getSunByObjJobs(node1,upmsPermissions,jobsPermissions));
                    folders1.add(node1);
                }
                if (folders1.size() > 0) {
                    ((JSONObject) system).put("children", folders1);
                }
            }
        }
        return systems;
    }

    private JSONArray getSunByObjJobs(JSONObject obj, List<UpmsPermission> upmsPermissions, List<UpmsJobsPermission> jobsPermissions){
        JSONArray array = new JSONArray();
        for (UpmsPermission upmsPermission: upmsPermissions) {
            if (upmsPermission.getPid().intValue() != obj.getIntValue("id") ) {
                continue;
            }
            JSONObject node = new JSONObject();
            node.put("id", upmsPermission.getPermissionId());
            node.put("name", upmsPermission.getName());
            node.put("open", true);
            for (UpmsJobsPermission jobsPermission : jobsPermissions) {
                if (jobsPermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                    node.put("checked", true);
                }
            }
            JSONArray suns = getSunByObjJobs(node, upmsPermissions, jobsPermissions);
            if(suns==null||suns.size()==0){
                //return node3;
            }else{
                node.put("children", suns);
            }

            array.add(node);
        }

        return array;

    }

}