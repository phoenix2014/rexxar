package com.rexxar.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.rexxar.dao.model.CrawlerTask;
import com.rexxar.dao.model.CrawlerTaskExample;

public interface CrawlerTaskMapper {
    int countByExample(CrawlerTaskExample example);

    int deleteByExample(CrawlerTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrawlerTask record);

    int insertSelective(CrawlerTask record);

    List<CrawlerTask> selectByExample(CrawlerTaskExample example);

    CrawlerTask selectByPrimaryKey(Long id);
    
    List<Long> selectIDs(CrawlerTaskExample example);

    int updateByExampleSelective(@Param("record") CrawlerTask record, @Param("example") CrawlerTaskExample example);

    int updateByExample(@Param("record") CrawlerTask record, @Param("example") CrawlerTaskExample example);

    int updateByPrimaryKeySelective(CrawlerTask record);

    int updateByPrimaryKey(CrawlerTask record);
}