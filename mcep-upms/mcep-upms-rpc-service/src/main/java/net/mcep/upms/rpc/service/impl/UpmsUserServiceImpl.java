package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsJobsMapper;
import net.mcep.upms.dao.mapper.UpmsOrganizationMapper;
import net.mcep.upms.dao.mapper.UpmsSystemMapper;
import net.mcep.upms.dao.mapper.UpmsUserMapper;
import net.mcep.upms.dao.model.*;
import net.mcep.upms.rpc.api.UpmsApiService;
import net.mcep.upms.rpc.api.UpmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UpmsUserService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;

    @Autowired
    UpmsApiService upmsApiService;

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

    @Autowired
    UpmsJobsMapper upmsJobsMapper;

    @Autowired
    UpmsSystemMapper upmsSystemMapper;

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(upmsUser.getUsername());
        long count = upmsUserMapper.countByExample(upmsUserExample);
        if (count > 0) {
            return null;
        }
        upmsUserMapper.insert(upmsUser);
        return upmsUser;
    }


    @Override
    public JSONArray getTreeByResourceId(Integer resourceId) {
//        // 资源已有用户
//        List<UpmsResourceUser> resourceUsers = upmsApiService.selectUpmsResourceUserByUpmsResourceId(resourceId);
//
//        JSONArray users = new JSONArray();
//        // 用户
//        UpmsUserExample upmsUserExample = new UpmsUserExample();
////        upmsUserExample.createCriteria()
////                .andStatusEqualTo((byte) 1);
//     //   upmsUserExample.setOrderByClause("userId asc");
//        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
//        for (UpmsUser upmsuser : upmsUsers) {
//            JSONObject node = new JSONObject();
//            node.put("id", upmsuser.getUserId());
//            node.put("name", upmsuser.getRealname());
//            for (UpmsResourceUser resourceUser : resourceUsers) {
//                if (resourceUser.getUserId().intValue() == upmsuser.getUserId().intValue()) {
//                    node.put("checked", true);
//                }
//            }
//            node.put("open", true);
//            users.add(node);
//        }
//        return users;
//    }

//        // 资源已有岗位和用户
        List<UpmsResourceJobs> resourceJobss = upmsApiService.selectUpmsResourceJobsUserByUpmsResourceId(resourceId);

        JSONArray organizations = new JSONArray();
        // 组织(部门)
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
                //这里开始查询岗位表和用户
                UpmsJobsExample upmsJobsExample = new UpmsJobsExample();
                ///这里加条件
                upmsJobsExample.createCriteria()
                        .andOrganizationIdEqualTo(((JSONObject) organization).getIntValue("id"));

                //       upmsJobsExample.setOrderByClause("orders asc");
                List<UpmsJobs> upmsJobs = upmsJobsMapper.selectByExample1(upmsJobsExample);
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
                            node.put("nocheck", true);
                        }
                    }
                    jobss.add(node);



                    // 人员
                    JSONArray person = new JSONArray();
                    for (Object jobs : jobss) {
                        for (UpmsJobs upmsJob2: upmsJobs) {
//                            if (upmsPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || upmsPermission2.getType() != 2) {
//                                continue;
//                            }
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsJob2.getUserId());
                            node2.put("name", upmsJob2.getRealname());
                            node2.put("open", true);
                            for (UpmsResourceJobs resourceJobs : resourceJobss) {
                                if (resourceJobs.getUserId().intValue() == upmsJob2.getUserId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            person.add(node2);
                        }
                        if (person.size() > 0) {
                            ((JSONObject) jobs).put("children", person);
                            person = new JSONArray();
                        }
                    }

                }
                if (jobss.size() > 0) {
                    ((JSONObject) organization).put("children", jobss);
                }
            }
        }
        return organizations;
    }


}