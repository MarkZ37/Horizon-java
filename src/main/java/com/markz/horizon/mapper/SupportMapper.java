package com.markz.horizon.mapper;

import com.markz.horizon.entity.dao.Support;
import java.util.List;

public interface SupportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Support record);

    Support selectByPrimaryKey(Integer id);

    List<Support> selectAll();

    int updateByPrimaryKey(Support record);

    Support selectByUserNameAndId(Support record);
}