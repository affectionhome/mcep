package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsResourceJobsMapper;
import net.mcep.upms.dao.model.UpmsResourceJobs;
import net.mcep.upms.dao.model.UpmsResourceJobsExample;
import net.mcep.upms.rpc.api.UpmsResourceJobsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* UpmsResourceJobsService实现
* Created by shuzheng on 2018/1/26.
*/
@Service
@Transactional
@BaseService
public class UpmsResourceJobsServiceImpl extends BaseServiceImpl<UpmsResourceJobsMapper, UpmsResourceJobs, UpmsResourceJobsExample> implements UpmsResourceJobsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceJobsServiceImpl.class);

    @Autowired
    UpmsResourceJobsMapper upmsResourceJobsMapper;


    @Override
    public int resourceJobs(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsResourceJobs upmsResourceJobs = new UpmsResourceJobs();
                upmsResourceJobs.setResourceId(id);
                upmsResourceJobs.setJobsId(json.getIntValue("id"));
                upmsResourceJobsMapper.insertSelective(upmsResourceJobs);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsResourceJobsExample upmsResourceJobsExample = new UpmsResourceJobsExample();
            upmsResourceJobsExample.createCriteria()
                    .andJobsIdIn(deleteIds)
                    .andResourceIdEqualTo(id);
            upmsResourceJobsMapper.deleteByExample(upmsResourceJobsExample);
        }
        return datas.size();
    }


    @Override
    public int resourceJobs1(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增资源
                UpmsResourceJobs upmsResourceJobs = new UpmsResourceJobs();
                upmsResourceJobs.setResourceId(json.getIntValue("id"));
                upmsResourceJobs.setJobsId(id);
                upmsResourceJobsMapper.insertSelective(upmsResourceJobs);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsResourceJobsExample upmsResourceJobsExample = new UpmsResourceJobsExample();
            upmsResourceJobsExample.createCriteria()
                    .andJobsIdEqualTo(id)
                    .andResourceIdIn(deleteIds);
            upmsResourceJobsMapper.deleteByExample(upmsResourceJobsExample);
        }
        return datas.size();
    }

}