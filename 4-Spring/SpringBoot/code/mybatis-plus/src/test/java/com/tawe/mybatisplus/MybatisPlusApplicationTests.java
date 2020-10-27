package com.tawe.mybatisplus;

import com.tawe.mybatisplus.entity.User;
import com.tawe.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectList() {
        System.out.println("-------- Select All Test -----------");
        // Wrapper 条件封装器 MP 的 selectList() 方法是内置的
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("David");
        user.setAge(18);
        user.setEmail("tawe@qq.com");

        int col = userMapper.insert(user);
        System.out.println(col);
        System.out.println(user);
    }

    @Test
    public void testUpdateById(){

        User user = new User();
        user.setId(1L);
        user.setAge(28);

        int result = userMapper.updateById(user);
        System.out.println(result);

    }

    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1321036140195209217L);

        user.setName("David Tang");
        user.setAge(20);
        // 模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
        // 此时不会出异常 但是数据不会被更新 避免 写失效
        // user.setVersion(user.getVersion()-2);
        System.out.println(user);
        userMapper.updateById(user);
    }

}
