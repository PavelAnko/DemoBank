package com.demo_banking.controller;

import com.demo_banking.models.Account;
import com.demo_banking.models.Replenishment;
import com.demo_banking.models.Transact;
import com.demo_banking.repository.AccountRepository;
import com.demo_banking.repository.ReplenishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class RefillController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ReplenishmentRepository replenishmentRepository;

    @PostMapping("/refill_balance")
    public String refillCard(@RequestParam("select_card") String selectCard,
                             @RequestParam("select_amount") String selectAmount,
                             HttpSession session){
        BigDecimal amountToAdd = new BigDecimal(selectAmount);
        Account account = (Account) session.getAttribute("accounts");
        String currency = "";
        if (selectCard.equalsIgnoreCase("usd_card")) {
            BigDecimal currentBalance = account.getBalance_usd();
            BigDecimal newBalance = currentBalance.add(amountToAdd);
            account.setBalance_usd(newBalance);
            currency = "USD";
        } else if (selectCard.equalsIgnoreCase("uah_card")) {
            BigDecimal currentBalance = account.getBalance_uah();
            BigDecimal newBalance = currentBalance.add(amountToAdd);
            account.setBalance_uah(newBalance);
            currency = "UAH";
        }
        accountRepository.save(account);
        saveRefill(account, null, currency, amountToAdd);
        session.setAttribute("accounts", accountRepository.findById(account.getAccount_id()).orElse(null));
        return "redirect:/app/dashboard";
    }

    private void saveRefill(Account sender, Account recipient, String currency, BigDecimal amount) {
        Replenishment replenishment = new Replenishment();
        replenishment.setAccount_id(sender.getAccount_id());
        replenishment.setReplenishment_type("Refilled");
        replenishment.setAmount(amount.doubleValue());
        replenishment.setSource(currency);
        replenishment.setStatus("Success");
        replenishment.setCreated_at(LocalDateTime.now());
        replenishmentRepository.save(replenishment);
    }
}
