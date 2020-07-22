package com.markz.horizon;

import com.markz.horizon.entity.User;
import com.markz.horizon.mapper.UserMapper;
import org.junit.jupiter.api.Test;
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

//    依赖注入
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectAll();
        for (User user:users) {
            System.out.println(user.getNickname());
        }

    }
    @Test
    public void testSelectByPK() {
        System.out.println("aaaaaaaaaaa:"+userMapper.selectByPrimaryKey(100000)+"aaaaaa");
    }

}
