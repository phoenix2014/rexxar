package com.rexxar.dao;

import com.rexxar.dao.model.ReaderUser;
import com.rexxar.dao.model.ReaderUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReaderUserMapper {
    int countByExample(ReaderUserExample example);

    int deleteByExample(ReaderUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReaderUser record);

    int insertSelective(ReaderUser record);

    List<ReaderUser> selectByExample(ReaderUserExample example);

    ReaderUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReaderUser record, @Param("example") ReaderUserExample example);

    int updateByExample(@Param("record") ReaderUser record, @Param("example") ReaderUserExample example);

    int updateByPrimaryKeySelective(ReaderUser record);

    int updateByPrimaryKey(ReaderUser record);
}