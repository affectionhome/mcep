package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsResourceUserMapper;
import net.mcep.upms.dao.model.UpmsResourceUser;
import net.mcep.upms.dao.model.UpmsResourceUserExample;
import net.mcep.upms.dao.model.UpmsRolePermission;
import net.mcep.upms.dao.model.UpmsRolePermissionExample;
import net.mcep.upms.rpc.api.UpmsResourceUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* UpmsResourceUserService实现
* Created by shuzheng on 2018/1/26.
*/
@Service
@Transactional
@BaseService
public class UpmsResourceUserServiceImpl extends BaseServiceImpl<UpmsResourceUserMapper, UpmsResourceUser, UpmsResourceUserExample> implements UpmsResourceUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceUserServiceImpl.class);

    @Autowired
    UpmsResourceUserMapper upmsResourceUserMapper;

    @Override
    public int resourceUser(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsResourceUser upmsResourceUser = new UpmsResourceUser();
                upmsResourceUser.setResourceId(id);
                upmsResourceUser.setUserId(json.getIntValue("id"));
                upmsResourceUserMapper.insertSelective(upmsResourceUser);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsResourceUserExample upmsResourceUserExample = new UpmsResourceUserExample();
            upmsResourceUserExample.createCriteria()
                    .andResourceIdIn(deleteIds)
                    .andResourceIdEqualTo(id);
            upmsResourceUserMapper.deleteByExample(upmsResourceUserExample);
        }
        return datas.size();
    }



    @Override
    public int resourceUser1(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsResourceUser upmsResourceUser = new UpmsResourceUser();
                upmsResourceUser.setResourceId(json.getIntValue("id"));
                upmsResourceUser.setUserId(id);
                upmsResourceUserMapper.insertSelective(upmsResourceUser);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsResourceUserExample upmsResourceUserExample = new UpmsResourceUserExample();
            upmsResourceUserExample.createCriteria()
                    .andUserIdEqualTo(id)
                    .andResourceIdIn(deleteIds);
            upmsResourceUserMapper.deleteByExample(upmsResourceUserExample);
        }
        return datas.size();
    }


}