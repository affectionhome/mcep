package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsJobsOrganization;
import net.mcep.upms.dao.model.UpmsJobsOrganizationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsJobsOrganizationMapper {
    long countByExample(UpmsJobsOrganizationExample example);

    int deleteByExample(UpmsJobsOrganizationExample example);

    int deleteByPrimaryKey(Integer jobsOrganizationId);

    int insert(UpmsJobsOrganization record);

    int insertSelective(UpmsJobsOrganization record);

    List<UpmsJobsOrganization> selectByExample(UpmsJobsOrganizationExample example);

    UpmsJobsOrganization selectByPrimaryKey(Integer jobsOrganizationId);

    int updateByExampleSelective(@Param("record") UpmsJobsOrganization record, @Param("example") UpmsJobsOrganizationExample example);

    int updateByExample(@Param("record") UpmsJobsOrganization record, @Param("example") UpmsJobsOrganizationExample example);

    int updateByPrimaryKeySelective(UpmsJobsOrganization record);

    int updateByPrimaryKey(UpmsJobsOrganization record);
}