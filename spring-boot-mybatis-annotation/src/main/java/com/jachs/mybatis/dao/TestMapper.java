package com.jachs.mybatis.dao;

import com.jachs.mybatis.entity.Test;

public interface TestMapper {
    int deleteByPrimaryKey(String name);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}