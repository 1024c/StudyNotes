package com.tawe.service.edu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName MyMetaObjectHandler
 * @Description 自动注入初始属性
 * @Author davidt
 * @Date 11/3/2020 2:24 PM
 * @Version 1.0
 **/

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate", new Timestamp(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("gmtModified", new Timestamp(System.currentTimeMillis()), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Timestamp(System.currentTimeMillis()), metaObject);
    }
}
