package com.rexxar.dao;

import com.rexxar.dao.model.NovelDirectory;
import com.rexxar.dao.model.NovelDirectoryExample;
import com.rexxar.dao.model.NovelDirectoryWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NovelDirectoryMapper {
    int countByExample(NovelDirectoryExample example);

    int deleteByExample(NovelDirectoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NovelDirectoryWithBLOBs record);

    int insertSelective(NovelDirectoryWithBLOBs record);

    List<NovelDirectoryWithBLOBs> selectByExampleWithBLOBs(NovelDirectoryExample example);

    List<NovelDirectory> selectByExample(NovelDirectoryExample example);

    NovelDirectoryWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NovelDirectoryWithBLOBs record, @Param("example") NovelDirectoryExample example);

    int updateByExampleWithBLOBs(@Param("record") NovelDirectoryWithBLOBs record, @Param("example") NovelDirectoryExample example);

    int updateByExample(@Param("record") NovelDirectory record, @Param("example") NovelDirectoryExample example);

    int updateByPrimaryKeySelective(NovelDirectoryWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NovelDirectoryWithBLOBs record);

    int updateByPrimaryKey(NovelDirectory record);
}