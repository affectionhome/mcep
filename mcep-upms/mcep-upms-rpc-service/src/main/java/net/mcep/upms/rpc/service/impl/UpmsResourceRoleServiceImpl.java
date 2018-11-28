package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsResourceRoleMapper;
import net.mcep.upms.dao.model.UpmsResourceRole;
import net.mcep.upms.dao.model.UpmsResourceRoleExample;
import net.mcep.upms.dao.model.UpmsRolePermission;
import net.mcep.upms.dao.model.UpmsRolePermissionExample;
import net.mcep.upms.rpc.api.UpmsResourceRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* UpmsResourceRoleService实现
* Created by shuzheng on 2018/1/28.
*/
@Service
@Transactional
@BaseService
public class UpmsResourceRoleServiceImpl extends BaseServiceImpl<UpmsResourceRoleMapper, UpmsResourceRole, UpmsResourceRoleExample> implements UpmsResourceRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceRoleServiceImpl.class);

    @Autowired
    UpmsResourceRoleMapper upmsResourceRoleMapper;


    @Override
    public int resourceRole(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsResourceRole upmsResourceRole = new UpmsResourceRole();
                upmsResourceRole.setResourceId(id);
                upmsResourceRole.setRoleId(json.getIntValue("id"));
                upmsResourceRoleMapper.insertSelective(upmsResourceRole);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsResourceRoleExample upmsResourceRoleExample = new UpmsResourceRoleExample();
            upmsResourceRoleExample.createCriteria()
                    .andRoleIdIn(deleteIds)
                    .andResourceIdEqualTo(id);
            upmsResourceRoleMapper.deleteByExample(upmsResourceRoleExample);
        }
        return datas.size();
    }


    @Override
    public int resourceRole1(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsResourceRole upmsResourceRole = new UpmsResourceRole();
                upmsResourceRole.setResourceId(json.getIntValue("id"));
                upmsResourceRole.setRoleId(id);
                upmsResourceRoleMapper.insertSelective(upmsResourceRole);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsResourceRoleExample upmsResourceRoleExample = new UpmsResourceRoleExample();
            upmsResourceRoleExample.createCriteria()
                    .andRoleIdEqualTo(id)
                    .andResourceIdIn(deleteIds);
            upmsResourceRoleMapper.deleteByExample(upmsResourceRoleExample);
        }
        return datas.size();
    }


}