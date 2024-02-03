package com.demo_banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/login_with_an_existing_user")
    public String loginWithExistingUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        return "redirect:/dashboard";
    }
}
