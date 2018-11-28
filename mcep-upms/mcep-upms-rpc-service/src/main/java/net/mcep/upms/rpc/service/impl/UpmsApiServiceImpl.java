package net.mcep.upms.rpc.service.impl;

import net.mcep.upms.dao.mapper.*;
import net.mcep.upms.dao.model.*;
import net.mcep.upms.rpc.api.UpmsApiService;
import net.mcep.upms.rpc.api.UpmsUserRoleService;
import net.mcep.upms.rpc.mapper.UpmsApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * UpmsApiService实现
 * Created by shuzheng on 2016/01/19.
 */
@Service
@Transactional
public class UpmsApiServiceImpl implements UpmsApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;

    @Autowired
    UpmsApiMapper upmsApiMapper;

    @Autowired
    UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Autowired
    UpmsUserPermissionMapper upmsUserPermissionMapper;

    @Autowired
    UpmsSystemMapper upmsSystemMapper;

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

    @Autowired
    UpmsLogMapper upmsLogMapper;

    @Autowired
    UpmsResourceUserMapper upmsResourceUserMapper;

    @Autowired
    UpmsResourceJobsMapper upmsResourceJobsMapper;

    @Autowired
    UpmsResourceRoleMapper upmsResourceRoleMapper;

    @Autowired
    UpmsJobsPermissionMapper upmsJobsPermissionMapper;
    @Autowired
    UpmsUserRoleService upmsUserRoleService;

    @Override
    public UpmsUser selectUpmsUserByid(Integer upmsUserId) {
        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if(upmsUser == null)
            return null;
        return upmsUser;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            LOGGER.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsPermission> upmsPermissions = upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
        return upmsPermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    @Cacheable(value = "zheng-upms-rpc-service-ehcache", key = "'selectUpmsPermissionByUpmsUserId_' + #upmsUserId")
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        return selectUpmsPermissionByUpmsUserId(upmsUserId);
    }

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            LOGGER.info("selectUpmsRoleByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsRole> upmsRoles = upmsApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
        return upmsRoles;
    }

    /**
     * 根据角色id获取用户id的集合
     * @param roleId
     * @return
     */
    @Override
    public List<String> selectUserIdsByRoleId(Integer roleId) {
        if(roleId == null){
            return null;
        }
        List<String> users = new ArrayList<>();
        UpmsUserRoleExample example = new UpmsUserRoleExample();
        example.or().andRoleIdEqualTo(roleId);
        List<UpmsUserRole> upmsUserRoles = upmsUserRoleService.selectByExample(example);
        for(UpmsUserRole user : upmsUserRoles) {
            users.add(user.getUserId().toString());
        }
        return users;
    }

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    @Override
    @Cacheable(value = "zheng-upms-rpc-service-ehcache", key = "'selectUpmsRoleByUpmsUserId_' + #upmsUserId")
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        return selectUpmsRoleByUpmsUserId(upmsUserId);
    }

    /**
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria()
                .andRoleIdEqualTo(upmsRoleId);
        List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);
        return upmsRolePermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(upmsUserId);
        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);
        return upmsUserPermissions;
    }

    /**
     * 根据条件获取系统数据
     * @param upmsSystemExample
     * @return
     */
    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.selectByExample(upmsSystemExample);
    }

    /**
     * 根据条件获取组织数据
     * @param upmsOrganizationExample
     * @return
     */
    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        return upmsOrganizationMapper.selectByExample(upmsOrganizationExample);
    }

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(username);
        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        return upmsLogMapper.insertSelective(record);
    }


    /**
     * 根据资源id获取所拥有的用户
     * @param upmsResourceId
     * @return
     */
    @Override
    public List<UpmsResourceUser> selectUpmsResourceUserByUpmsResourceId(Integer upmsResourceId) {
        UpmsResourceUserExample upmsResourceUserExample = new UpmsResourceUserExample();
        upmsResourceUserExample.createCriteria()
                .andResourceIdEqualTo(upmsResourceId);
        List<UpmsResourceUser> upmsResourceUsers = upmsResourceUserMapper.selectByExample(upmsResourceUserExample);
        return upmsResourceUsers;
    }


    /**
     * 根据资源id获取所拥有的岗位
     * @param upmsResourceId
     * @return
     */
    @Override
    public List<UpmsResourceJobs> selectUpmsResourceJobsByUpmsResourceId(Integer upmsResourceId) {
        UpmsResourceJobsExample upmsResourceJobsExample = new UpmsResourceJobsExample();
        upmsResourceJobsExample.createCriteria()
                .andResourceIdEqualTo(upmsResourceId);
        List<UpmsResourceJobs> upmsResourceJobs = upmsResourceJobsMapper.selectByExample(upmsResourceJobsExample);
        return upmsResourceJobs;
    }

    /**
     * 根据资源id获取所拥有的岗位和用户
     * @param upmsResourceId
     * @return
     */
    @Override
    public List<UpmsResourceJobs> selectUpmsResourceJobsUserByUpmsResourceId(Integer upmsResourceId) {
        UpmsResourceJobsExample upmsResourceJobsExample = new UpmsResourceJobsExample();
        upmsResourceJobsExample.createCriteria()
                .andResourceIdEqualTo1(upmsResourceId);
        List<UpmsResourceJobs> upmsResourceJobs = upmsResourceJobsMapper.selectByExample1(upmsResourceJobsExample);
        return upmsResourceJobs;
    }


    /**
     * 根据资源id获取所拥有的角色
     * @param upmsResourceId
     * @return
     */
    @Override
    public List<UpmsResourceRole> selectUpmsResourceRoleByUpmsResourceId(Integer upmsResourceId) {
        UpmsResourceRoleExample upmsResourceRoleExample = new UpmsResourceRoleExample();
        upmsResourceRoleExample.createCriteria()
                .andResourceIdEqualTo(upmsResourceId);
        List<UpmsResourceRole> upmsResourceRoles = upmsResourceRoleMapper.selectByExample(upmsResourceRoleExample);
        return upmsResourceRoles;
    }


    /**
     * 根据角色id获取所拥有的资源
     * @param upmsRoleId
     * @return
     */
    @Override
    public List<UpmsResourceRole> selectUpmsRoleResourceByUpmsRoleId(Integer upmsRoleId) {
        UpmsResourceRoleExample upmsResourceRoleExample = new UpmsResourceRoleExample();
        upmsResourceRoleExample.createCriteria()
                .andRoleIdEqualTo(upmsRoleId);
        List<UpmsResourceRole> upmsResourceRoles = upmsResourceRoleMapper.selectByExample(upmsResourceRoleExample);
        return upmsResourceRoles;
    }



    /**
     * 根据岗位id获取所拥有的资源
     * @param upmsJobsId
     * @return
     */
    @Override
    public List<UpmsResourceJobs> selectUpmsJobsResourceByUpmsJobsId(Integer upmsJobsId) {
        UpmsResourceJobsExample upmsResourceJobsExample = new UpmsResourceJobsExample();
        upmsResourceJobsExample.createCriteria()
                .andJobsIdEqualTo(upmsJobsId);
        List<UpmsResourceJobs> upmsResourceJobss = upmsResourceJobsMapper.selectByExample(upmsResourceJobsExample);
        return upmsResourceJobss;
    }


    /**
     * 根据用户id获取所拥有的资源
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsResourceUser> selectUpmsUserResourceByUpmsUserId(Integer upmsUserId) {
        UpmsResourceUserExample upmsResourceUserExample = new UpmsResourceUserExample();
        upmsResourceUserExample.createCriteria()
                .andUserIdEqualTo(upmsUserId);
        List<UpmsResourceUser> upmsResourceUsers = upmsResourceUserMapper.selectByExample(upmsResourceUserExample);
        return upmsResourceUsers;
    }



    /**
     * 根据岗位id获取所拥有的权限
     * @param upmsJobsId
     * @return
     */
    @Override
    public List<UpmsJobsPermission> selectUpmsJobsPermisstionByUpmsJobsId(Integer upmsJobsId) {
        UpmsJobsPermissionExample upmsJobsPermissionExample = new UpmsJobsPermissionExample();
        upmsJobsPermissionExample.createCriteria()
                .andJobsIdEqualTo(upmsJobsId);
        List<UpmsJobsPermission> upmsJobsPermissions = upmsJobsPermissionMapper.selectByExample(upmsJobsPermissionExample);
        return upmsJobsPermissions;
    }




}