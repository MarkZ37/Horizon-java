package com.markz.horizon.mapper;

import com.markz.horizon.entity.dao.Webfollow;
import java.util.List;

public interface WebfollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Webfollow record);

    Webfollow selectByPrimaryKey(Integer id);

    List<Webfollow> selectAll();

    int updateByPrimaryKey(Webfollow record);

    Webfollow selectByUserName(String username,String flusername);

    int deletByUserName(String username,String flusername);
}