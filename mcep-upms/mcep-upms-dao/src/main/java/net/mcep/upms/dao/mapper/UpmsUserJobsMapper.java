package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsUserJobs;
import net.mcep.upms.dao.model.UpmsUserJobsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsUserJobsMapper {
    long countByExample(UpmsUserJobsExample example);

    int deleteByExample(UpmsUserJobsExample example);

    int deleteByPrimaryKey(Integer userJobsId);

    int insert(UpmsUserJobs record);

    int insertSelective(UpmsUserJobs record);

    List<UpmsUserJobs> selectByExample(UpmsUserJobsExample example);

    UpmsUserJobs selectByPrimaryKey(Integer userJobsId);

    int updateByExampleSelective(@Param("record") UpmsUserJobs record, @Param("example") UpmsUserJobsExample example);

    int updateByExample(@Param("record") UpmsUserJobs record, @Param("example") UpmsUserJobsExample example);

    int updateByPrimaryKeySelective(UpmsUserJobs record);

    int updateByPrimaryKey(UpmsUserJobs record);
}