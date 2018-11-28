package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsDataPermission;
import net.mcep.upms.dao.model.UpmsDataPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsDataPermissionMapper {
    long countByExample(UpmsDataPermissionExample example);

    int deleteByExample(UpmsDataPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UpmsDataPermission record);

    int insertSelective(UpmsDataPermission record);

    List<UpmsDataPermission> selectByExample(UpmsDataPermissionExample example);

    UpmsDataPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UpmsDataPermission record, @Param("example") UpmsDataPermissionExample example);

    int updateByExample(@Param("record") UpmsDataPermission record, @Param("example") UpmsDataPermissionExample example);

    int updateByPrimaryKeySelective(UpmsDataPermission record);

    int updateByPrimaryKey(UpmsDataPermission record);
}