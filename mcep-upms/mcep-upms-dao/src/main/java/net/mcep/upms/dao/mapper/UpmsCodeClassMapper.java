package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsCodeClass;
import net.mcep.upms.dao.model.UpmsCodeClassExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsCodeClassMapper {
    long countByExample(UpmsCodeClassExample example);

    int deleteByExample(UpmsCodeClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UpmsCodeClass record);

    int insertSelective(UpmsCodeClass record);

    List<UpmsCodeClass> selectByExample(UpmsCodeClassExample example);

    UpmsCodeClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UpmsCodeClass record, @Param("example") UpmsCodeClassExample example);

    int updateByExample(@Param("record") UpmsCodeClass record, @Param("example") UpmsCodeClassExample example);

    int updateByPrimaryKeySelective(UpmsCodeClass record);

    int updateByPrimaryKey(UpmsCodeClass record);
}