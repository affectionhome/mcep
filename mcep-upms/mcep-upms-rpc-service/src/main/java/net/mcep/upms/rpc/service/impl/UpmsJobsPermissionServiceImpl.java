package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsJobsPermissionMapper;
import net.mcep.upms.dao.model.UpmsJobsPermission;
import net.mcep.upms.dao.model.UpmsJobsPermissionExample;
import net.mcep.upms.rpc.api.UpmsJobsPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* UpmsJobsPermissionService实现
* Created by shuzheng on 2018/4/13.
*/
@Service
@Transactional
@BaseService
public class UpmsJobsPermissionServiceImpl extends BaseServiceImpl<UpmsJobsPermissionMapper, UpmsJobsPermission, UpmsJobsPermissionExample> implements UpmsJobsPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsJobsPermissionServiceImpl.class);

    @Autowired
    UpmsJobsPermissionMapper upmsJobsPermissionMapper;


    @Override
    public int jobsPermission(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsJobsPermission upmsJobsPermission = new UpmsJobsPermission();
                upmsJobsPermission.setJobsId(id);
                upmsJobsPermission.setPermissionId(json.getIntValue("id"));
                upmsJobsPermissionMapper.insertSelective(upmsJobsPermission);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsJobsPermissionExample upmsRolePermissionExample = new UpmsJobsPermissionExample();
            upmsRolePermissionExample.createCriteria()
                    .andPermissionIdIn(deleteIds)
                    .andJobsIdEqualTo(id);
            upmsJobsPermissionMapper.deleteByExample(upmsRolePermissionExample);
        }
        return datas.size();
    }


}