package net.mcep.cms.dao.mapper;

import net.mcep.cms.dao.model.CmsTag;
import net.mcep.cms.dao.model.CmsTagExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CmsTagMapper {
    long countByExample(CmsTagExample example);

    int deleteByExample(CmsTagExample example);

    int deleteByPrimaryKey(Integer tagId);

    int insert(CmsTag record);

    int insertSelective(CmsTag record);

    List<CmsTag> selectByExample(CmsTagExample example);

    CmsTag selectByPrimaryKey(Integer tagId);

    int updateByExampleSelective(@Param("record") CmsTag record, @Param("example") CmsTagExample example);

    int updateByExample(@Param("record") CmsTag record, @Param("example") CmsTagExample example);

    int updateByPrimaryKeySelective(CmsTag record);

    int updateByPrimaryKey(CmsTag record);
}