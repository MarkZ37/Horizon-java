package com.markz.horizon.mapper;

import com.markz.horizon.entity.Article;
import java.util.List;

public interface ArticleMapper {
    int insert(Article record);

    List<Article> selectAll();

    List<Article> selectByOpenId(String openid);
}