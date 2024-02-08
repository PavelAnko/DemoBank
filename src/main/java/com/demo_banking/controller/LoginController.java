package com.demo_banking.controller;

import com.demo_banking.models.User;
import com.demo_banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email")String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){

        String getEmailInDatabase = userRepository.getUserEmail(email);

        if(getEmailInDatabase == null ){
            model.addAttribute("error", "Incorrect Email or Password");
            return "login";
        }

        String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase);
        if(!BCrypt.checkpw(password, getPasswordInDatabase)){
            model.addAttribute("error", "Incorrect Email or Password");
            return "login";
        }

        User users = userRepository.getUserDetails(getEmailInDatabase);
        session.setAttribute("users", users);
        session.setAttribute("authenticated", true);

        return "redirect:/app/dashboard";

    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
