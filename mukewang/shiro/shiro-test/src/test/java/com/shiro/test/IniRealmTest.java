package com.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * IniRealm认证测试
 * <p>
 * Created by yaoyao on 2018-09-03.
 */
public class IniRealmTest {

    @Test
    public void testAuthentication() {

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

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
        subject.checkPermission("user:update");

    }

}
