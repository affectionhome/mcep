package net.mcep.upms.rpc.api;


import net.mcep.upms.dao.model.*;

import java.util.List;

/**
 * upms系统接口
 * Created by shuzheng on 2017/2/11.
 */
public interface UpmsApiService {

    /**
     * 根据用户id获取用户的基本信息
     * @return
     */
    UpmsUser selectUpmsUserByid(Integer upmsUserId);


    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

    List<String> selectUserIdsByRoleId(Integer roleId);

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId);

    /**
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId);

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据条件获取系统数据
     * @param upmsSystemExample
     * @return
     */
    List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample);

    /**
     * 根据条件获取组织数据
     * @param upmsOrganizationExample
     * @return
     */
    List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample);

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    UpmsUser selectUpmsUserByUsername(String username);

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    int insertUpmsLogSelective(UpmsLog record);


    /**
     * 根据资源id获取所拥有的用户
     * @param upmsResourceId
     * @return
     */
    List<UpmsResourceUser> selectUpmsResourceUserByUpmsResourceId(Integer upmsResourceId);


    /**
     * 根据资源id获取所拥有的岗位
     * @param upmsResourceId
     * @return
     */
    List<UpmsResourceJobs> selectUpmsResourceJobsByUpmsResourceId(Integer upmsResourceId);

    /**
     * 根据资源id获取所拥有的岗位和用户
     * @param upmsResourceId
     * @return
     */
    List<UpmsResourceJobs> selectUpmsResourceJobsUserByUpmsResourceId(Integer upmsResourceId);

    /**
     * 根据资源id获取所拥有的角色
     * @param upmsResourceId
     * @return
     */
    List<UpmsResourceRole> selectUpmsResourceRoleByUpmsResourceId(Integer upmsResourceId);

    /**
     * 根据角色id获取所拥有的资源
     * @param upmsRoleId
     * @return
     */
    List<UpmsResourceRole> selectUpmsRoleResourceByUpmsRoleId(Integer upmsRoleId);

    /**
     * 根据岗位id获取所拥有的资源
     * @param upmsJobsId
     * @return
     */
    List<UpmsResourceJobs> selectUpmsJobsResourceByUpmsJobsId(Integer upmsJobsId);

    /**
     * 根据用户id获取所拥有的资源
     * @param upmsUserId
     * @return
     */
    List<UpmsResourceUser> selectUpmsUserResourceByUpmsUserId(Integer upmsUserId);


    /**
     * 根据岗位id获取所拥有的权限
     * @param upmsJobsId
     * @return
     */
    List<UpmsJobsPermission> selectUpmsJobsPermisstionByUpmsJobsId(Integer upmsJobsId);

}
