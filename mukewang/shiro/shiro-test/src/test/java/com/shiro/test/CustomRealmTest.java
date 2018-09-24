package com.shiro.test;

import com.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by yaoyao on 2018-09-24.
 */
public class CustomRealmTest {

    @Test
    public void testAuthentication() {

        CustomRealm customRealm = new CustomRealm();

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken("yaoyao", "123456");

        //登录
        subject.login(token);
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        //验证角色
        subject.checkRole("admin");

        //验证权限
        subject.checkPermission("user:add");

    }

}