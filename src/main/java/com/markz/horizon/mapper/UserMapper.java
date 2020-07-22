package com.markz.horizon.mapper;

import com.markz.horizon.entity.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer account);

    int insert(User record);

    User selectByPrimaryKey(Integer account);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}