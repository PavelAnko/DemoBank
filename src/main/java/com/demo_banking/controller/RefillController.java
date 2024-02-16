package com.demo_banking.controller;

import com.demo_banking.service.SaveToDBService;
import com.demo_banking.models.Account;
import com.demo_banking.models.User;
import com.demo_banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class RefillController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SaveToDBService saveToDataBase;

    @PostMapping("/refill_balance")
    public String refillCard(@RequestParam("select_card") String selectCard,
                             @RequestParam("select_amount") String selectAmount,
                             HttpSession session){
        BigDecimal amountToAdd = new BigDecimal(selectAmount);
        Account account = (Account) session.getAttribute("accounts");
        User user = (User) session.getAttribute("users");
        String currency = "";
        String listed_on = "";
        if (selectCard.equalsIgnoreCase("usd_card")) {
            BigDecimal currentBalance = account.getBalance_usd();
            BigDecimal newBalance = currentBalance.add(amountToAdd);
            account.setBalance_usd(newBalance);
            currency = "USD";
            listed_on = "USD";
        } else if (selectCard.equalsIgnoreCase("uah_card")) {
            BigDecimal currentBalance = account.getBalance_uah();
            BigDecimal newBalance = currentBalance.add(amountToAdd);
            account.setBalance_uah(newBalance);
            currency = "UAH";
            listed_on = "UAH";
        }
        accountRepository.save(account);
        saveToDataBase.saveRefill(account, user, currency, listed_on, amountToAdd);
        session.setAttribute("accounts", accountRepository.findById(account.getAccount_id()).orElse(null));
        return "redirect:/app/dashboard";
    }
}
