package net.mcep.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mcep.common.annotation.BaseService;
import net.mcep.common.base.BaseServiceImpl;
import net.mcep.upms.dao.mapper.UpmsCodeMapper;
import net.mcep.upms.dao.mapper.UpmsResourceMapper;
import net.mcep.upms.dao.mapper.UpmsResourceUserMapper;
import net.mcep.upms.dao.model.*;
import net.mcep.upms.rpc.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* UpmsResourceService实现
* Created by shuzheng on 2018/1/18.
*/
@Service
@Transactional
@BaseService
public class UpmsResourceServiceImpl extends BaseServiceImpl<UpmsResourceMapper, UpmsResource, UpmsResourceExample> implements UpmsResourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsResourceServiceImpl.class);

    @Autowired
    UpmsResourceMapper upmsResourceMapper;

    @Autowired
    UpmsApiService upmsApiService;

    @Autowired
    UpmsCodeMapper upmsCodeMapper;


    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        // 角色已有资源
        List<UpmsResourceRole> resourceRoles = upmsApiService.selectUpmsRoleResourceByUpmsRoleId(roleId);

        JSONArray codes = new JSONArray();
        // upms_code表  得到总目录
        UpmsCodeExample upmsCodeExample = new UpmsCodeExample();
//        upmsSystemExample.createCriteria()
//                .andStatusEqualTo((byte) 1);
//        upmsSystemExample.setOrderByClause("orders asc");
        upmsCodeExample.createCriteria().andCatNoEqualTo("resource");
        List<UpmsCode> upmsCodes = upmsCodeMapper.selectByExample(upmsCodeExample);
        for (UpmsCode upmsCode : upmsCodes) {
            JSONObject node = new JSONObject();
            node.put("id", upmsCode.getCdNo());
            node.put("name", upmsCode.getCdName());
            node.put("nocheck", true);
            node.put("open", true);
            codes.add(node);
        }

        if(codes.size() > 0){

            for (Object code: codes) {
                //这里开始查询角色资源
                UpmsCodeExample upmsCodeExample1 = new UpmsCodeExample();
                 ///这里加条件
                upmsCodeExample1.createCriteria()
                        .andCdNoEqualTo1(((JSONObject) code).getString("id"));

                //       upmsJobsExample.setOrderByClause("orders asc");
                List<UpmsCode> upmsCodes1 = upmsCodeMapper.selectByExample1(upmsCodeExample1);
                if (upmsCodes1.size() == 0) {
                    continue;
                }
                // 资源
                JSONArray resources = new JSONArray();
                for (UpmsCode upmsCode1: upmsCodes1) {
//                    if (upmsJob.getOrganizationId().intValue() != 0 ) {
//                        continue;
//                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsCode1.getResourceId()); //资源id
                    node.put("name", upmsCode1.getResourceName()); // 资源名
                    node.put("open", true);
                    if(upmsCode1.getResourceName() == "" || upmsCode1.getResourceName() == null){

                    }else{
                        for (UpmsResourceRole resourceRole : resourceRoles) {
                            if (resourceRole.getResourceId().intValue() == upmsCode1.getResourceId().intValue()) {
                                node.put("checked", true);
                            }
                        }
                        resources.add(node);
                    }


                }
                if (resources.size() > 0) {
                    ((JSONObject) code).put("children", resources);
                }
            }

        }


        return codes;
    }



    @Override
    public JSONArray getTreeByJobsId(Integer jobsId) {
        // 岗位已有资源
        List<UpmsResourceJobs> resourceJobss = upmsApiService.selectUpmsJobsResourceByUpmsJobsId(jobsId);

        JSONArray codes = new JSONArray();
        // upms_code表  得到总目录
        UpmsCodeExample upmsCodeExample = new UpmsCodeExample();
//        upmsSystemExample.createCriteria()
//                .andStatusEqualTo((byte) 1);
//        upmsSystemExample.setOrderByClause("orders asc");
        upmsCodeExample.createCriteria().andCatNoEqualTo("resource");
        List<UpmsCode> upmsCodes = upmsCodeMapper.selectByExample(upmsCodeExample);
        for (UpmsCode upmsCode : upmsCodes) {
            JSONObject node = new JSONObject();
            node.put("id", upmsCode.getCdNo());
            node.put("name", upmsCode.getCdName());
            node.put("nocheck", true);
            node.put("open", true);
            codes.add(node);
        }

        if(codes.size() > 0){

            for (Object code: codes) {
                //这里开始查询角色资源
                UpmsCodeExample upmsCodeExample1 = new UpmsCodeExample();
                ///这里加条件
                upmsCodeExample1.createCriteria()
                        .andCdNoEqualTo1(((JSONObject) code).getString("id"));

                //       upmsJobsExample.setOrderByClause("orders asc");
                List<UpmsCode> upmsCodes1 = upmsCodeMapper.selectByExample1(upmsCodeExample1);
                if (upmsCodes1.size() == 0) {
                    continue;
                }
                // 资源
                JSONArray resources = new JSONArray();
                for (UpmsCode upmsCode1: upmsCodes1) {
//                    if (upmsJob.getOrganizationId().intValue() != 0 ) {
//                        continue;
//                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsCode1.getResourceId()); //资源id
                    node.put("name", upmsCode1.getResourceName()); // 资源名
                    node.put("open", true);
                    if(upmsCode1.getResourceName() == "" || upmsCode1.getResourceName() == null){

                    }else{
                        for (UpmsResourceJobs resourceJobs : resourceJobss) {
                            if (resourceJobs.getResourceId().intValue() == upmsCode1.getResourceId().intValue()) {
                                node.put("checked", true);
                            }
                        }
                        resources.add(node);

                    }

                }
                if (resources.size() > 0) {
                    ((JSONObject) code).put("children", resources);
                }
            }

        }


        return codes;
    }




    @Override
    public JSONArray getTreeByUserId(Integer userId) {
        // 岗位已有资源
        List<UpmsResourceUser> resourceUsers = upmsApiService.selectUpmsUserResourceByUpmsUserId(userId);

        JSONArray codes = new JSONArray();
        // upms_code表  得到总目录
        UpmsCodeExample upmsCodeExample = new UpmsCodeExample();
//        upmsSystemExample.createCriteria()
//                .andStatusEqualTo((byte) 1);
//        upmsSystemExample.setOrderByClause("orders asc");
        upmsCodeExample.createCriteria().andCatNoEqualTo("resource");
        List<UpmsCode> upmsCodes = upmsCodeMapper.selectByExample(upmsCodeExample);
        for (UpmsCode upmsCode : upmsCodes) {
            JSONObject node = new JSONObject();
            node.put("id", upmsCode.getCdNo());
            node.put("name", upmsCode.getCdName());
            node.put("nocheck", true);
            node.put("open", true);
            codes.add(node);
        }

        if(codes.size() > 0){

            for (Object code: codes) {
                //这里开始查询角色资源
                UpmsCodeExample upmsCodeExample1 = new UpmsCodeExample();
                ///这里加条件
                upmsCodeExample1.createCriteria()
                        .andCdNoEqualTo1(((JSONObject) code).getString("id"));

                //       upmsJobsExample.setOrderByClause("orders asc");
                List<UpmsCode> upmsCodes1 = upmsCodeMapper.selectByExample1(upmsCodeExample1);
                if (upmsCodes1.size() == 0) {
                    continue;
                }
                // 资源
                JSONArray resources = new JSONArray();
                for (UpmsCode upmsCode1: upmsCodes1) {
//                    if (upmsJob.getOrganizationId().intValue() != 0 ) {
//                        continue;
//                    }
                    JSONObject node = new JSONObject();
                    node.put("id", upmsCode1.getResourceId()); //资源id
                    node.put("name", upmsCode1.getResourceName()); // 资源名
                    node.put("open", true);
                    if(upmsCode1.getResourceName() == "" || upmsCode1.getResourceName() == null){

                    }else{
                        for (UpmsResourceUser resourceUser : resourceUsers) {
                            if (resourceUser.getResourceId().intValue() == upmsCode1.getResourceId().intValue()) {
                                node.put("checked", true);
                            }
                        }
                        resources.add(node);
                    }


                }
                if (resources.size() > 0) {
                    ((JSONObject) code).put("children", resources);
                }
            }

        }


        return codes;
    }

    @Override
    public String getResourceByUserId(Integer userId, String type) {

        UpmsResourceUserExample userExample = new UpmsResourceUserExample();
        userExample.or().andUserIdEqualTo(userId);
        List<UpmsResource> list = upmsResourceMapper.getResourceByUserId(userId,type);
        StringBuilder sb = new StringBuilder();
        for(UpmsResource upmsResource :list) {
            Integer bid = upmsResource.getBusinessId();
            sb.append(bid).append(",");
        }
        if(sb.length()>0){
            return sb.substring(0,sb.length()-1).toString();
        }
        return null;
    }

}