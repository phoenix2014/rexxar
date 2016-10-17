package com.rexxar.dao;

import com.rexxar.dao.model.NovelOverview;
import com.rexxar.dao.model.NovelOverviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NovelOverviewMapper {
    int countByExample(NovelOverviewExample example);

    int deleteByExample(NovelOverviewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NovelOverview record);

    int insertSelective(NovelOverview record);

    List<NovelOverview> selectByExample(NovelOverviewExample example);

    NovelOverview selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NovelOverview record, @Param("example") NovelOverviewExample example);

    int updateByExample(@Param("record") NovelOverview record, @Param("example") NovelOverviewExample example);

    int updateByPrimaryKeySelective(NovelOverview record);

    int updateByPrimaryKey(NovelOverview record);
}