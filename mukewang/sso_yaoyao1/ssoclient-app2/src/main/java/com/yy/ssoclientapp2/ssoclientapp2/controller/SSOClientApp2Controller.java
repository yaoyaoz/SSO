package com.yy.ssoclientapp2.ssoclientapp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yaoyao on 2018/7/26.
 */
@Controller
public class SSOClientApp2Controller {

    @RequestMapping("/index")
    public String main() {
        return "index";
    }

}
