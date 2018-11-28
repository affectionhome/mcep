package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsJobs;
import net.mcep.upms.dao.model.UpmsJobsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsJobsMapper {
    long countByExample(UpmsJobsExample example);

    int deleteByExample(UpmsJobsExample example);

    int deleteByPrimaryKey(Integer jobsId);

    int insert(UpmsJobs record);

    int insertSelective(UpmsJobs record);

    List<UpmsJobs> selectByExample(UpmsJobsExample example);

    List<UpmsJobs> selectByExample1(UpmsJobsExample example);

    UpmsJobs selectByPrimaryKey(Integer jobsId);

    int updateByExampleSelective(@Param("record") UpmsJobs record, @Param("example") UpmsJobsExample example);

    int updateByExample(@Param("record") UpmsJobs record, @Param("example") UpmsJobsExample example);

    int updateByPrimaryKeySelective(UpmsJobs record);

    int updateByPrimaryKey(UpmsJobs record);
}