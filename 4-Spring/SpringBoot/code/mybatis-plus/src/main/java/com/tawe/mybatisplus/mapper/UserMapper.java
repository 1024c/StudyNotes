package com.tawe.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tawe.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Administrator
 * @Date 10/27/2020 4:27 PM
 * @Version 1.0
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
