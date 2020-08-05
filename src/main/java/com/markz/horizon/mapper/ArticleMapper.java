package com.markz.horizon.mapper;

import com.markz.horizon.entity.Article;
import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(String openid);

    int insert(Article record);

    Article selectByPrimaryKey(String openid);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    List<Article> selectByOpenId(String openid);
}