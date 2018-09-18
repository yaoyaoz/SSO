数据库:
用户名:root
密码:123456

ddl:
create database shiro;

use shiro;

create table users
(
    username varchar(64) not null,
    password varchar(64) not null
);

create table user_roles
(
    username varchar(64) not null,
    role_name varchar(64) not null
);

create table roles_permissions
(
    role_name  varchar(64) not null,
    permission varchar(64) not null
);