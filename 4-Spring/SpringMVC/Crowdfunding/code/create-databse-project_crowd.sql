create database `project_crowd`;
use `project_crowd`;

# 创建 Admin 表
drop table if exists `t_admin`;
create table `t_admin`
(
    id int not null auto_increment,     #主键
    login_acct varchar(255) not null,   #登录账号
    user_pswd char(32) not null,        #登录密码
    user_name varchar(255) not null,    #昵称
    email varchar(255) not null,        #邮件地址
    create_time char(19),               #创建时间
    primary key(id)
);

# 创建 Role 表
drop table if exists `t_role`;
create table `t_role`
(
    id int not null auto_increment,     #主键
    role_name varchar(255) not null,    #角色名称
    primary key(id)
);

# 创建 Menu 表
drop table if exists `t_menu`;
create table `t_menu`
(
    id int not null auto_increment,     #主键
    pid int,                            #父节点
    menu_name varchar(255) not null,    #菜单名称
    url varchar(255),                   #菜单 URL
    icon varchar(255),                  #ICON
    primary key(id)
);

truncate t_menu;

insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('1',NULL,'系统权限菜单','glyphicon glyphicon-th-list',NULL);
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('2','1','控制面板','glyphicon glyphicon-dashboard','main.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('3','1','权限管理','glyphicon glyphiconglyphicon-tasks',NULL);
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('4','3','用户维护','glyphicon glyphicon-user','user/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('5','3','角色维护','glyphicon glyphicon-king','role/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('6','3','菜单维护','glyphicon glyphicon-lock','permission/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('7','1','业务审核','glyphicon glyphicon-ok',NULL);
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('8','7','实名认证审核','glyphicon glyphicon-check','auth_cert/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('9','7','广告审核','glyphicon glyphicon-check','auth_adv/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('10','7','项目审核','glyphicon glyphicon-check','auth_project/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('11','1','业务管理','glyphicon glyphicon-th-large',NULL);
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('12','11','资质维护','glyphicon glyphicon-picture','cert/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('13','11','分类管理','glyphicon glyphicon-equalizer','cert_type/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('14','11','流程管理','glyphicon glyphicon-random','process/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('15','11','广告管理','glyphicon glyphicon-hdd','advert/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('16','11','消息模板','glyphicon glyphicon-comment','message/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('17','11','项目分类','glyphicon glyphicon-list','project_type/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('18','11','项目标签','glyphicon glyphicon-tags','tag/index.htm');
insert into `t_menu` (`id`,`pid`,`menu_name`,`icon`,`url`) values ('19','1','参数管理','glyphicon glyphicon-list-alt','param/index.htm');

# 创建 Admin-Role 中间表
drop table if exists `inner_admin_role`;
create table `inner_admin_role` (
    admin_id int not null,          #用户 ID
    role_id int not null,           #角色 ID
    primary key(admin_id, role_id)  #使用联合主键
);

# 创建 Auth 表
CREATE TABLE `t_auth` (
    id int(11) NOT NULL AUTO_INCREMENT,
    auth_name varchar(200) DEFAULT NULL,
    title varchar(200) DEFAULT NULL,
    category_id int(11) DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO `t_auth`(id,auth_name,title,category_id) VALUES(1,'','用户模块',NULL);
INSERT INTO `t_auth`(id,auth_name,title,category_id) VALUES(2,'user:delete','删除',1);
INSERT INTO `t_auth`(id,auth_name,title,category_id) VALUES(3,'user:get','查询',1);
INSERT INTO `t_auth`(id,auth_name,title,category_id) VALUES(4,'','角色模块',NULL);
INSERT INTO `t_auth`(id,auth_name,title,category_id) VALUES(5,'role:delete','删除',4);
INSERT INTO `t_auth`(id,auth_name,title,category_id) VALUES(6,'role:get','查询',4);
INSERT INTO `t_auth`(id,auth_name,title,category_id) VALUES(7,'role:add','新增',4);

# 创建 inner_role_auth 表
CREATE TABLE `inner_role_auth` (
role_id INT NOT NULL,
auth_id INT NOT NULL,
PRIMARY KEY (role_id, auth_id)
);