package com.yaoyao.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public void subLogin(User user, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                response.getWriter().write(e.getMessage());
                return;
            }
            response.getWriter().write("登录成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
