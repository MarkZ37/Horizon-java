package com.markz.horizon;

import com.markz.horizon.entity.dao.Webarticle;
import com.markz.horizon.mapper.WebarticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HorizonApplication.class)
@Transactional
@Rollback
class HorizonApplicationTests {

    @Autowired
    WebarticleMapper webarticleMapper;
    @Autowired
    Webarticle webarticle;
    @Test
    public void testWebArticleInsert(){

    }

}
