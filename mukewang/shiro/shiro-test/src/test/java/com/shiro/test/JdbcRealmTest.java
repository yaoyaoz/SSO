package com.shiro.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import javax.sql.DataSource;

/**
 * JdbcRealm认证测试
 *
 * Created by yaoyao on 2018-09-08.
 */
public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    @Test
    public void testAuthentication() {

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);

        //权限开关：默认为false，就不会去数据库查权限
        jdbcRealm.setPermissionsLookupEnabled(true);

        /** 自定义认证查询sql */
//        String authenticationSql = "select password from test_users where username = ?";
//        jdbcRealm.setAuthenticationQuery(authenticationSql);

        /** 自定义用户角色查询sql */
//        String roleSql = "select role_name from test_user_roles where username = ?";
//        jdbcRealm.setUserRolesQuery(roleSql);

        /** 自定义角色权限查询sql */
//        String permissionSql = "select permission1 from test_roles_permissions where role_name = ?";
//        jdbcRealm.setPermissionsQuery(permissionSql);

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("yaoyao", "123456");
//        AuthenticationToken token = new UsernamePasswordToken("xiaoming", "123456");

        //登录
        subject.login(token);
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

//        subject.checkRole("admin");
        subject.checkRoles("admin", "user");
//        subject.checkRoles("user");

        subject.checkPermission("user:select");
//        subject.checkPermission("user:update");

    }

}
