package com.tawe.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName User
 * @Description User 实体类
 * @Author Administrator
 * @Date 10/27/2020 4:26 PM
 * @Version 1.0
 **/

@Data
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    /** 自动填充 **/
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;


    /** 用于乐观锁的 version 字段 */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
