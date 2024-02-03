package com.demo_banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String getDashboard(){
        return "dashboard";
    }
}