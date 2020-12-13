package com.example.login_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("changePassword")
    public String change(){
        return "change";
    }

}
