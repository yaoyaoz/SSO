package com.ssoclient.app1.ssoclientapp1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by yaoyao on 2018/7/20.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "Hello spring boot! 耶";
    }

}
