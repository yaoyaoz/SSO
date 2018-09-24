数据库:
用户名:root
密码:123456

ddl:
create database shiro;

use shiro;

-- shiro默认要执行的sql
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

create table roles_permissions (
    role_name varchar(64) not null,
    permission varchar(64) not null
);

-- 自定义sql
create table test_users
(
    username varchar(64) not null,
    password varchar(64) not null
);

create table test_user_roles
(
    username varchar(64) not null,
    role_name varchar(64) not null
);

create table test_roles_permissions(
    role_name varchar(64) not null,
    permission1 varchar(64) not null
);