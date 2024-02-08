package com.demo_banking.controller;

import com.demo_banking.auxiliary_methods.GenAccountNumber;
import com.demo_banking.models.Account;
import com.demo_banking.models.User;
import com.demo_banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class DashboardController {
        @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/dashboard")
    public ModelAndView getDashboard(HttpSession session){
        ModelAndView getDashboardPage = new ModelAndView("dashboard");
        User users = (User) session.getAttribute("users");

        if (users == null) {
            return new ModelAndView("redirect:/login");
        }

        String uniqueCardNumberUSD = String.valueOf(GenAccountNumber.generateAccountNumber());
        String uniqueCardNumberUAH = String.valueOf(GenAccountNumber.generateAccountNumber());
//        List<Account> userAccounts = accountRepository.getAccountById(user.getUser_id());

        String usdCardNumber = "";
        String uahCardNumber = "";

        if (accountRepository.existsByAccountUsdNumber(uniqueCardNumberUSD)==true){
            uniqueCardNumberUSD = String.valueOf(GenAccountNumber.generateAccountNumber());
        }

        if (accountRepository.existsByAccountUahNumber(uniqueCardNumberUAH)==true){
            uniqueCardNumberUAH = String.valueOf(GenAccountNumber.generateAccountNumber());
        }

        Optional<Account> existingAccountOptional = accountRepository.findByUserEmail(users.getEmail());
        if (existingAccountOptional.isPresent()){
            Account account = existingAccountOptional.get();
            account.setBalance_usd(account.getBalance_usd());
            account.setBalance_uah(account.getBalance_uah());
            accountRepository.save(account);
            usdCardNumber = account.getAccount_usd_number();
            uahCardNumber = account.getAccount_uah_number();
            getDashboardPage.addObject("accounts", account);
            session.setAttribute("accounts", account);
        }

        else{
            Account account = new Account();
            account.setAccount_usd_number(uniqueCardNumberUSD);
            account.setAccount_uah_number(uniqueCardNumberUAH);
            account.setBalance_usd(account.getBalance_usd());
            account.setBalance_uah(account.getBalance_uah());
            account.setUser_id(users.getUser_id());
            account.setUser_email(users.getEmail());
            accountRepository.save(account);
            usdCardNumber = uniqueCardNumberUSD;
            uahCardNumber = uniqueCardNumberUAH;
            getDashboardPage.addObject("accounts", account);
            session.setAttribute("accounts", account);
        }

        BigDecimal totalUsdBalance = accountRepository.getUsdBalance(users.getUser_id());
        BigDecimal totalUahBalance = accountRepository.getUahBalance(users.getUser_id());


//        getDashboardPage.addObject("userAccounts", userAccounts);
        getDashboardPage.addObject("totalUsdBalance", totalUsdBalance);
        getDashboardPage.addObject("totalUahBalance", totalUahBalance);
        getDashboardPage.addObject("usdCardNumber", usdCardNumber);
        getDashboardPage.addObject("uahCardNumber", uahCardNumber);

        return getDashboardPage;
    }

//    @PostMapping("/dashboard")
//    public String redirectToDashboard() {
//        return "accounts_display";
//    }
}
