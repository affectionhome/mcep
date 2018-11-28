package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsRoleMapper;
import net.mcep.upms.dao.mapper.UpmsUserRoleMapper;
import net.mcep.upms.dao.model.*;
import net.mcep.upms.rpc.api.UpmsApiService;
import net.mcep.upms.rpc.api.UpmsRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UpmsRoleService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsRoleServiceImpl.class);

    @Autowired
    UpmsRoleMapper upmsRoleMapper;

    @Autowired
    UpmsApiService upmsApiService;

    @Autowired
    UpmsUserRoleMapper upmsUserRoleMapper;

    @Override
    public JSONArray getTreeByResourceId(Integer resourceId) {
        // 资源已有角色
        List<UpmsResourceRole> resourceRoles = upmsApiService.selectUpmsResourceRoleByUpmsResourceId(resourceId);

        JSONArray roles = new JSONArray();
        // 角色
        UpmsRoleExample upmsRoleExample = new UpmsRoleExample();
//        upmsUserExample.createCriteria()
//                .andStatusEqualTo((byte) 1);
     //   upmsUserExample.setOrderByClause("userId asc");
        List<UpmsRole> upmsRoles = upmsRoleMapper.selectByExample(upmsRoleExample);
        for (UpmsRole upmsRole : upmsRoles) {
            JSONObject node = new JSONObject();
            node.put("id", upmsRole.getRoleId());
            node.put("name", upmsRole.getName());
            for (UpmsResourceRole resourceRole : resourceRoles) {
                if (resourceRole.getRoleId().intValue() == upmsRole.getRoleId().intValue()) {
                    node.put("checked", true);
                }
            }
            node.put("open", true);
            roles.add(node);
        }
        return roles;
    }

    @Override
    public int updateAndCommit(int id, List<UpmsUserRole> userRoles) {
        // 删除原来角色所对应的用户
        UpmsUserRoleExample example = new UpmsUserRoleExample();
        example.or().andRoleIdEqualTo(id);
        upmsUserRoleMapper.deleteByExample(example);

        int count = 0;
        for (UpmsUserRole userRole : userRoles) {
            userRole.setRoleId(id);
            upmsUserRoleMapper.insertSelective(userRole);
            count++;
        }
        return count;
    }


}