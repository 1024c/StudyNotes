package com.tawe.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.mybatisplus.entity.User;
import com.tawe.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(MybatisPlusApplicationTests.class);

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
    @Test
    public void testSelectFunctions() {
        // 根据 ID 查询
        User user1 = userMapper.selectById(1L);
        // 根据多个 IDs 查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));

        // 根据 多个 条件 进行查询
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("name", "David Tang2");

        userMapper.selectByMap(map);

    }

    @Test
    public void testSelectPage() {
        Page<User> userPage = userMapper.selectPage(new Page<>(3,5), null);
        System.out.println(userPage);
    }

    @Test
    public void testLogicDelete() {
        int col = userMapper.deleteById(1321280763316477954L);
    }

    @Test
    public void testSelectWrapper() {
        // new LambdaQueryWrapper();
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        queryWrapper.eq(User::getName, "David Tang2").or(s -> s.eq(User::getName, "David"));
        // 只选择主键
        // queryWrapper.select(s -> false);
        queryWrapper.select(s -> s.getProperty().startsWith("n"));
        // queryWrapper.last("SELECT * FROM USER");
        // userMapper.selectList(queryWrapper)
        logger.warn(queryWrapper::getSqlSelect);
        logger.warn(queryWrapper::getSqlSegment);




        // QueryWrapper<User> wrapper = new QueryWrapper<>();
        // wrapper.eq("name", "David Tang2");
        // userMapper.selectList(wrapper);
    }

}
