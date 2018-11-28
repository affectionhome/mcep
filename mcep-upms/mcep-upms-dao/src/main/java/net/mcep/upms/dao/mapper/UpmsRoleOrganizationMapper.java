package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsRoleOrganization;
import net.mcep.upms.dao.model.UpmsRoleOrganizationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsRoleOrganizationMapper {
    long countByExample(UpmsRoleOrganizationExample example);

    int deleteByExample(UpmsRoleOrganizationExample example);

    int deleteByPrimaryKey(Integer roleOrganizationId);

    int insert(UpmsRoleOrganization record);

    int insertSelective(UpmsRoleOrganization record);

    List<UpmsRoleOrganization> selectByExample(UpmsRoleOrganizationExample example);

    UpmsRoleOrganization selectByPrimaryKey(Integer roleOrganizationId);

    int updateByExampleSelective(@Param("record") UpmsRoleOrganization record, @Param("example") UpmsRoleOrganizationExample example);

    int updateByExample(@Param("record") UpmsRoleOrganization record, @Param("example") UpmsRoleOrganizationExample example);

    int updateByPrimaryKeySelective(UpmsRoleOrganization record);

    int updateByPrimaryKey(UpmsRoleOrganization record);
}