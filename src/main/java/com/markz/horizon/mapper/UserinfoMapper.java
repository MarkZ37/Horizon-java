package com.markz.horizon.mapper;

import com.markz.horizon.entity.Userinfo;
import java.util.List;

public interface UserinfoMapper {
    int deleteByPrimaryKey(String openid);

    int insert(Userinfo record);

    Userinfo selectByPrimaryKey(String openid);

    List<Userinfo> selectAll();

    int updateByPrimaryKey(Userinfo record);
}