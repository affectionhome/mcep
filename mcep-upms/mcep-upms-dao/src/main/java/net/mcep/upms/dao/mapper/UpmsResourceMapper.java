package net.mcep.upms.dao.mapper;

import net.mcep.upms.dao.model.UpmsResource;
import net.mcep.upms.dao.model.UpmsResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsResourceMapper {
    long countByExample(UpmsResourceExample example);

    int deleteByExample(UpmsResourceExample example);

    int deleteByPrimaryKey(Integer resourceId);

    int insert(UpmsResource record);

    int insertSelective(UpmsResource record);

    List<UpmsResource> selectByExample(UpmsResourceExample example);

    UpmsResource selectByPrimaryKey(Integer resourceId);

    int updateByExampleSelective(@Param("record") UpmsResource record, @Param("example") UpmsResourceExample example);

    int updateByExample(@Param("record") UpmsResource record, @Param("example") UpmsResourceExample example);

    int updateByPrimaryKeySelective(UpmsResource record);

    int updateByPrimaryKey(UpmsResource record);

    List<UpmsResource> getResourceByUserId(@Param("userid") Integer userid,@Param("type") String type);
}