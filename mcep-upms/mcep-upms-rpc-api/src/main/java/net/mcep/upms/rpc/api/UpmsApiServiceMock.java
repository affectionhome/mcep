package net.mcep.upms.rpc.api;

import net.mcep.upms.dao.model.*;
import net.mcep.upms.dao.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 降级实现UpmsApiService接口
 * Created by shuzheng on 2017/2/14.
 */
public class UpmsApiServiceMock implements UpmsApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsApiServiceMock.class);

    @Override
    public UpmsUser selectUpmsUserByid(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsPermissionByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsPermissionByUpmsUserIdByCache");
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsRoleByUpmsUserId");
        return null;
    }

    @Override
    public List<String> selectUserIdsByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsRoleByUpmsUserIdByCache");
        return null;
    }

    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsRolePermisstionByUpmsRoleId");
        return null;
    }

    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsUserPermissionByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsSystemByExample");
        return null;
    }

    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsOrganizationByExample");
        return null;
    }

    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsUserByUsername");
        return null;
    }

    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        LOGGER.info("UpmsApiServiceMock => insertSelective");
        return 0;
    }

    @Override
    public List<UpmsResourceUser> selectUpmsResourceUserByUpmsResourceId(Integer upmsResourceId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsResourceUserByUpmsResourceId");
        return null;
    }

    @Override
    public List<UpmsResourceJobs> selectUpmsResourceJobsByUpmsResourceId(Integer upmsResourceId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsResourceJobsByUpmsResourceId");
        return null;
    }

    @Override
    public List<UpmsResourceJobs> selectUpmsResourceJobsUserByUpmsResourceId(Integer upmsResourceId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsResourceJobsUserByUpmsResourceId");
        return null;
    }

    @Override
    public List<UpmsResourceRole> selectUpmsResourceRoleByUpmsResourceId(Integer upmsResourceId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsResourceRoleByUpmsResourceId");
        return null;
    }

    @Override
    public List<UpmsResourceRole> selectUpmsRoleResourceByUpmsRoleId(Integer upmsRoleId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsRoleResourceByUpmsRoleId");
        return null;
    }

    @Override
    public List<UpmsResourceJobs> selectUpmsJobsResourceByUpmsJobsId(Integer upmsJobsId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsJobsResourceByUpmsJobsId");
        return null;
    }

    @Override
    public List<UpmsResourceUser> selectUpmsUserResourceByUpmsUserId(Integer upmsUserId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsUserResourceByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsJobsPermission> selectUpmsJobsPermisstionByUpmsJobsId(Integer upmsJobsId) {
        LOGGER.info("UpmsApiServiceMock => selectUpmsJobsPermisstionByUpmsJobsId");
        return null;
    }

}
