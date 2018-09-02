package com.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Shiro认证测试
 *
 * Created by yaoyao on 2018-08-25.
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
//        simpleAccountRealm.addAccount("yaoyao", "123456");

        //权限的部分，后面再演示，因为simpleAccountRealm是不支持权限的
//        simpleAccountRealm.addAccount("yaoyao", "123456", "admin");
        simpleAccountRealm.addAccount("yaoyao", "123456", "admin", "user");

    }

    @Test
    public void testAuthentication() {

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("yaoyao", "123456");

        //登录
        subject.login(token);
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        //登出
//        subject.logout();
//        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        //验证角色
//        subject.checkRole("admin1");
        subject.checkRoles("admin", "user");

    }

}
