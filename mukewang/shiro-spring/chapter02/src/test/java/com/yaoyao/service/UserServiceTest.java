package com.yaoyao.service;

import com.yaoyao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by yaoyao on 2017-07-16.
 */
@ContextConfiguration("classpath*:/smart-context.xml") //启动Spring容器
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    private UserService userService;

    @Autowired    //注入Spring容器中的Bean
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testHasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void testFindUserByUserName() {
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

    @Test
    public void testAddLoginLog() {

    }

}
