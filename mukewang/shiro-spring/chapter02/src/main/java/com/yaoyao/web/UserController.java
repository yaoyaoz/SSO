package com.yaoyao.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yaoyao on 2018/9/27.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/login.html")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/subLogin.html", method = RequestMethod.POST)
    public String subLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                response.getWriter().write(e.getMessage());
                return null;
            }
            response.getWriter().write("登录成功");

            if (!subject.hasRole("admin")) {
                response.getWriter().write(" 没有admin权限");
                return null;
            }

            //登录成功后重定向到访问地址
            response.sendRedirect(WebUtils.getSavedRequest(request).getRequestUrl());
            return null;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "main")
    public void main(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        Subject subject = SecurityUtils.getSubject();
        try {
            response.getWriter().write("你好，" + subject.getPrincipal());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "test.html")
    public void test(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        Subject subject = SecurityUtils.getSubject();
        try {
            response.getWriter().write("测试页面，" + subject.getPrincipal());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "testRole.html")
    public void testRole(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        Subject subject = SecurityUtils.getSubject();
        try {
            response.getWriter().write("testRole success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresRoles("xxx")
    @RequestMapping(value = "testRole1.html")
    public void testRole1(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        Subject subject = SecurityUtils.getSubject();
        try {
            response.getWriter().write("testRole1 success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
