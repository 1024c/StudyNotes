create schema `mybatis_plus`;
use `mybatis_plus`;
drop table if exists user;
create table user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
)
    comment '用户表';

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');

# 增加自动填充字段 create_time, update_time
alter table user
    add create_time datetime null;

alter table user
    add update_time datetime null;

# 增加乐观锁 version 字段
ALTER TABLE `user` ADD COLUMN `version` INT;

# 新增 逻辑删除 字段
ALTER TABLE `user` ADD COLUMN `flag` INT DEFAULT 0;

UPDATE `user` SET flag=0 WHERE 1=1;