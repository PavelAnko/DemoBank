package com.demo_banking.controller;

import com.demo_banking.models.Account;
import com.demo_banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class RefillController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/top_up_balance")
    public String refillCard(@RequestParam("select_card") String selectCard,
                                   @RequestParam("select_amount") String selectAmount,
                                   HttpSession session){
        BigDecimal amountToAdd = new BigDecimal(selectAmount);
        Account account = (Account) session.getAttribute("accounts");
        if (selectCard.equalsIgnoreCase("usd_card")) {
            account.setBalance_usd(amountToAdd);
        } else if (selectCard.equalsIgnoreCase("uah_card")) {
            account.setBalance_uah(amountToAdd);
        } else {
            return "redirect:/app/dashboard";
        }
        accountRepository.save(account);
        return "redirect:/app/dashboard";
    }
}
