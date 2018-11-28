package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsJobsPermission;
import net.mcep.upms.dao.model.UpmsJobsPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsJobsPermissionMapper {
    long countByExample(UpmsJobsPermissionExample example);

    int deleteByExample(UpmsJobsPermissionExample example);

    int deleteByPrimaryKey(Integer jobsPermissionId);

    int insert(UpmsJobsPermission record);

    int insertSelective(UpmsJobsPermission record);

    List<UpmsJobsPermission> selectByExample(UpmsJobsPermissionExample example);

    UpmsJobsPermission selectByPrimaryKey(Integer jobsPermissionId);

    int updateByExampleSelective(@Param("record") UpmsJobsPermission record, @Param("example") UpmsJobsPermissionExample example);

    int updateByExample(@Param("record") UpmsJobsPermission record, @Param("example") UpmsJobsPermissionExample example);

    int updateByPrimaryKeySelective(UpmsJobsPermission record);

    int updateByPrimaryKey(UpmsJobsPermission record);
}