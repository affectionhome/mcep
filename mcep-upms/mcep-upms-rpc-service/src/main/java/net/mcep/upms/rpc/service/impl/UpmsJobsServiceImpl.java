package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsJobsMapper;
import net.mcep.upms.dao.mapper.UpmsOrganizationMapper;
import net.mcep.upms.dao.model.*;
import net.mcep.upms.rpc.api.UpmsApiService;
import net.mcep.upms.rpc.api.UpmsJobsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UpmsJobsService实现
* Created by shuzheng on 2018/1/18.
*/
@Service
@Transactional
@BaseService
public class UpmsJobsServiceImpl extends BaseServiceImpl<UpmsJobsMapper, UpmsJobs, UpmsJobsExample> implements UpmsJobsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsJobsServiceImpl.class);

    @Autowired
    UpmsJobsMapper upmsJobsMapper;

    @Autowired
    UpmsApiService upmsApiService;

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;


    @Override
    public JSONArray getTreeByResourceId(Integer resourceId) {
        // 资源已有岗位
        List<UpmsResourceJobs> resourceJobss = upmsApiService.selectUpmsResourceJobsByUpmsResourceId(resourceId);

        JSONArray organizations = new JSONArray();
        // 组织
        UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();


        List<UpmsOrganization> upmsOrganizations = upmsOrganizationMapper.selectByExample(upmsOrganizationExample);
        for (UpmsOrganization upmsOrganization : upmsOrganizations) {
            JSONObject node = new JSONObject();
            node.put("id", upmsOrganization.getOrganizationId());
            node.put("name", upmsOrganization.getName());
            node.put("nocheck", true);
            node.put("open", true);
            organizations.add(node);
        }

        if (organizations.size() > 0) {
            for (Object organization: organizations) {
                //这里开始查询岗位表
                UpmsJobsExample upmsJobsExample = new UpmsJobsExample();
                ///这里加条件
                upmsJobsExample.createCriteria()
                        .andOrganizationIdEqualTo(((JSONObject) organization).getIntValue("id"));

         //       upmsJobsExample.setOrderByClause("orders asc");
                List<UpmsJobs> upmsJobs = upmsJobsMapper.selectByExample(upmsJobsExample);
                if (upmsJobs.size() == 0) {
                    continue;
                }
                // 岗位
                JSONArray jobss = new JSONArray();
                for (UpmsJobs upmsJob: upmsJobs) {
//                    if (upmsJob.getOrganizationId().intValue() != 0 ) {
//                        continue;
//                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsJob.getJobsId());
                    node.put("name", upmsJob.getName());
                    node.put("open", true);
                    for (UpmsResourceJobs resourceJobs : resourceJobss) {
                        if (resourceJobs.getJobsId().intValue() == upmsJob.getJobsId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    jobss.add(node);

                }
                if (jobss.size() > 0) {
                    ((JSONObject) organization).put("children", jobss);
                }
            }
        }
        return organizations;
    }



}