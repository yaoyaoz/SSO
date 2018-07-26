package com.yy.ssoclient_app1.ssoclientapp1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yaoyao on 2018/7/26.
 */
@Controller
public class SSOClientApp1Controller {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/index")
    public String main() {
        System.out.println("port:" + port);
        return "index";
    }

}
