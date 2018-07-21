package com.ssoclient.app1.ssoclientapp1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by yaoyao on 2018/7/20.
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        return "Hello spring boot! 耶";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        request.getSession().setAttribute("user", principal.getName());
        return "index";
    }

}
