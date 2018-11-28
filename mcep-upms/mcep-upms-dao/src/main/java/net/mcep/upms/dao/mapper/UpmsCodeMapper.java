package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsCode;
import net.mcep.upms.dao.model.UpmsCodeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsCodeMapper {
    long countByExample(UpmsCodeExample example);

    int deleteByExample(UpmsCodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UpmsCode record);

    int insertSelective(UpmsCode record);

    List<UpmsCode> selectByExample(UpmsCodeExample example);

    List<UpmsCode> selectByExample1(UpmsCodeExample example);

    UpmsCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UpmsCode record, @Param("example") UpmsCodeExample example);

    int updateByExample(@Param("record") UpmsCode record, @Param("example") UpmsCodeExample example);

    int updateByPrimaryKeySelective(UpmsCode record);

    int updateByPrimaryKey(UpmsCode record);
}