package net.mcep.ted.dao.mapper;

import java.util.List;
import net.mcep.ted.dao.model.TedDemo;
import net.mcep.ted.dao.model.TedDemoExample;
import org.apache.ibatis.annotations.Param;

public interface TedDemoMapper {
    long countByExample(TedDemoExample example);

    int deleteByExample(TedDemoExample example);

    int deleteByPrimaryKey(Integer proprietorId);

    int insert(TedDemo record);

    int insertSelective(TedDemo record);

    List<TedDemo> selectByExample(TedDemoExample example);

    TedDemo selectByPrimaryKey(Integer proprietorId);

    int updateByExampleSelective(@Param("record") TedDemo record, @Param("example") TedDemoExample example);

    int updateByExample(@Param("record") TedDemo record, @Param("example") TedDemoExample example);

    int updateByPrimaryKeySelective(TedDemo record);

    int updateByPrimaryKey(TedDemo record);


    /**下面可以进行增加自己需要的接口*/
}