package com.markz.horizon.mapper;

import com.markz.horizon.entity.dao.Webarticle;
import java.util.List;

public interface WebarticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Webarticle record);

    Webarticle selectByPrimaryKey(Integer id);

    List<Webarticle> selectAll();

    int updateByPrimaryKey(Webarticle record);
}