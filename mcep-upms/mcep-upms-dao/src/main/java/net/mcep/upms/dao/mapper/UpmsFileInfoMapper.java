package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsFileInfo;
import net.mcep.upms.dao.model.UpmsFileInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsFileInfoMapper {
    long countByExample(UpmsFileInfoExample example);

    int deleteByExample(UpmsFileInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UpmsFileInfo record);

    int insertSelective(UpmsFileInfo record);

    List<UpmsFileInfo> selectByExample(UpmsFileInfoExample example);

    UpmsFileInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UpmsFileInfo record, @Param("example") UpmsFileInfoExample example);

    int updateByExample(@Param("record") UpmsFileInfo record, @Param("example") UpmsFileInfoExample example);

    int updateByPrimaryKeySelective(UpmsFileInfo record);

    int updateByPrimaryKey(UpmsFileInfo record);



}