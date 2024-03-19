package com.demo_banking.controller;
import com.demo_banking.models.User;
import com.demo_banking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public RegisterController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/new_user_save")
    public String saveUser(@RequestParam("first_name") String first_name,
                           @RequestParam("last_name") String last_name,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password) {
        String hashedPassword = passwordEncoder.encode(password);

        User users = new User();
        users.setFirst_name(first_name);
        users.setLast_name(last_name);
        users.setEmail(email);
        users.setPassword(hashedPassword);
        users.setData_created(new Date());
        userRepository.save(users);
        return "redirect:/app/dashboard";
    }
}
