package com.rexxar.dao;

import com.rexxar.dao.model.ReaderBookshelf;
import com.rexxar.dao.model.ReaderBookshelfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReaderBookshelfMapper {
    int countByExample(ReaderBookshelfExample example);

    int deleteByExample(ReaderBookshelfExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReaderBookshelf record);

    int insertSelective(ReaderBookshelf record);

    List<ReaderBookshelf> selectByExample(ReaderBookshelfExample example);

    ReaderBookshelf selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReaderBookshelf record, @Param("example") ReaderBookshelfExample example);

    int updateByExample(@Param("record") ReaderBookshelf record, @Param("example") ReaderBookshelfExample example);

    int updateByPrimaryKeySelective(ReaderBookshelf record);

    int updateByPrimaryKey(ReaderBookshelf record);
}