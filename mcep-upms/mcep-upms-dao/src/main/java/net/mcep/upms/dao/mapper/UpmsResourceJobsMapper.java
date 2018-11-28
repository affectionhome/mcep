package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsResourceJobs;
import net.mcep.upms.dao.model.UpmsResourceJobsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsResourceJobsMapper {
    long countByExample(UpmsResourceJobsExample example);

    int deleteByExample(UpmsResourceJobsExample example);

    int deleteByPrimaryKey(Integer resourceJobsId);

    int insert(UpmsResourceJobs record);

    int insertSelective(UpmsResourceJobs record);

    List<UpmsResourceJobs> selectByExample(UpmsResourceJobsExample example);

    List<UpmsResourceJobs> selectByExample1(UpmsResourceJobsExample example);

    UpmsResourceJobs selectByPrimaryKey(Integer resourceJobsId);

    int updateByExampleSelective(@Param("record") UpmsResourceJobs record, @Param("example") UpmsResourceJobsExample example);

    int updateByExample(@Param("record") UpmsResourceJobs record, @Param("example") UpmsResourceJobsExample example);

    int updateByPrimaryKeySelective(UpmsResourceJobs record);

    int updateByPrimaryKey(UpmsResourceJobs record);
}