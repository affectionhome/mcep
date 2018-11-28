package net.mcep.upms.dao.mapper;

import java.util.List;
import net.mcep.upms.dao.model.UpmsCompany;
import net.mcep.upms.dao.model.UpmsCompanyExample;
import org.apache.ibatis.annotations.Param;

public interface UpmsCompanyMapper {
    long countByExample(UpmsCompanyExample example);

    int deleteByExample(UpmsCompanyExample example);

    int deleteByPrimaryKey(Integer companyId);

    int insert(UpmsCompany record);

    int insertSelective(UpmsCompany record);

    List<UpmsCompany> selectByExampleWithBLOBs(UpmsCompanyExample example);

    List<UpmsCompany> selectByExample(UpmsCompanyExample example);

    UpmsCompany selectByPrimaryKey(Integer companyId);

    int updateByExampleSelective(@Param("record") UpmsCompany record, @Param("example") UpmsCompanyExample example);

    int updateByExampleWithBLOBs(@Param("record") UpmsCompany record, @Param("example") UpmsCompanyExample example);

    int updateByExample(@Param("record") UpmsCompany record, @Param("example") UpmsCompanyExample example);

    int updateByPrimaryKeySelective(UpmsCompany record);

    int updateByPrimaryKeyWithBLOBs(UpmsCompany record);

    int updateByPrimaryKey(UpmsCompany record);
}