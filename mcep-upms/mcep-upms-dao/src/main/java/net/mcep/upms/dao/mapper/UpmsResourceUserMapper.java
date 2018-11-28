package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsResourceUser;
import net.mcep.upms.dao.model.UpmsResourceUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsResourceUserMapper {
    long countByExample(UpmsResourceUserExample example);

    int deleteByExample(UpmsResourceUserExample example);

    int deleteByPrimaryKey(Integer resourceUserId);

    int insert(UpmsResourceUser record);

    int insertSelective(UpmsResourceUser record);

    List<UpmsResourceUser> selectByExample(UpmsResourceUserExample example);

    UpmsResourceUser selectByPrimaryKey(Integer resourceUserId);

    int updateByExampleSelective(@Param("record") UpmsResourceUser record, @Param("example") UpmsResourceUserExample example);

    int updateByExample(@Param("record") UpmsResourceUser record, @Param("example") UpmsResourceUserExample example);

    int updateByPrimaryKeySelective(UpmsResourceUser record);

    int updateByPrimaryKey(UpmsResourceUser record);
}