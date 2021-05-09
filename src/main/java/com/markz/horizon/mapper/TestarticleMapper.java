package com.markz.horizon.mapper;

import com.markz.horizon.entity.dao.Testarticle;
import java.util.List;

public interface TestarticleMapper {
    int insert(Testarticle record);

    List<Testarticle> selectAll();
}