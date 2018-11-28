package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsResourceRole;
import net.mcep.upms.dao.model.UpmsResourceRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsResourceRoleMapper {
    long countByExample(UpmsResourceRoleExample example);

    int deleteByExample(UpmsResourceRoleExample example);

    int deleteByPrimaryKey(Integer upmsResourceId);

    int insert(UpmsResourceRole record);

    int insertSelective(UpmsResourceRole record);

    List<UpmsResourceRole> selectByExample(UpmsResourceRoleExample example);

    UpmsResourceRole selectByPrimaryKey(Integer upmsResourceId);

    int updateByExampleSelective(@Param("record") UpmsResourceRole record, @Param("example") UpmsResourceRoleExample example);

    int updateByExample(@Param("record") UpmsResourceRole record, @Param("example") UpmsResourceRoleExample example);

    int updateByPrimaryKeySelective(UpmsResourceRole record);

    int updateByPrimaryKey(UpmsResourceRole record);
}