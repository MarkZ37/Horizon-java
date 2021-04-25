package com.markz.horizon.mapper;

import com.markz.horizon.entity.Useraccount;
import java.util.List;

public interface UseraccountMapper {
    int deleteByPrimaryKey(String username);

    int insert(Useraccount record);

    Useraccount selectByPrimaryKey(String username);

    List<Useraccount> selectAll();

    int updateByPrimaryKey(Useraccount record);
}