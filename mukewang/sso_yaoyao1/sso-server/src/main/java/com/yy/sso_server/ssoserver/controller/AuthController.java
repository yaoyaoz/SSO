package com.yy.sso_server.ssoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 授权控制器
 *
 * Created by yaoyao on 2018/7/26.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

}
