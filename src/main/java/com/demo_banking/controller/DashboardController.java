package com.demo_banking.controller;

import com.demo_banking.auxiliary_methods.GenAccountNumber;
import com.demo_banking.models.Account;
import com.demo_banking.models.Replenishment;
import com.demo_banking.models.Transact;
import com.demo_banking.models.User;
import com.demo_banking.repository.AccountRepository;
import com.demo_banking.repository.ReplenishmentRepository;
import com.demo_banking.repository.TransactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/app"})
public class DashboardController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactRepository transactRepository;
    @Autowired
    private ReplenishmentRepository replenishmentRepository;


    @GetMapping("/dashboard")
    public ModelAndView getDashboard(HttpSession session, Model model, @ModelAttribute("error") String error) {
        ModelAndView getDashboardPage = new ModelAndView("dashboard");
        User users = (User) session.getAttribute("users");

        if (users == null) {
            return new ModelAndView("redirect:/login");
        }

        String cardNumberUSD = String.valueOf(GenAccountNumber.generateAccountNumber());
        String cardNumberUAH = String.valueOf(GenAccountNumber.generateAccountNumber());

        String usdCardNumber = "";
        String uahCardNumber = "";

        Optional<Account> existingAccountOptional = accountRepository.findByUserEmail(users.getEmail());
        if (existingAccountOptional.isPresent()) {
            Account account = existingAccountOptional.get();
            account.setBalance_usd(account.getBalance_usd());
            account.setBalance_uah(account.getBalance_uah());
            accountRepository.save(account);
            usdCardNumber = account.getAccount_usd_number();
            uahCardNumber = account.getAccount_uah_number();
            getDashboardPage.addObject("accounts", account);
            session.setAttribute("accounts", account);
        } else {
            Account account = new Account();
            account.setAccount_usd_number(cardNumberUSD);
            account.setAccount_uah_number(cardNumberUAH);
            account.setBalance_usd(account.getBalance_usd());
            account.setBalance_uah(account.getBalance_uah());
            account.setUser_id(users.getUser_id());
            account.setUser_email(users.getEmail());
            usdCardNumber = cardNumberUSD;
            uahCardNumber = cardNumberUAH;
            accountRepository.save(account);
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

        model.addAttribute("error", error);
        return getDashboardPage;
    }

    @GetMapping("/dashboard/transact_history")
    public ModelAndView getTransactHistory(HttpSession session) {
        ModelAndView getTransactHistoryPage = new ModelAndView("transactHistory");
        Account account = (Account) session.getAttribute("accounts");
        List<Transact> userTransactHistory = transactRepository.findByAccountId(account.getAccount_id());
        getTransactHistoryPage.addObject("transact", userTransactHistory);
        return getTransactHistoryPage;
    }

    @GetMapping("/dashboard/replenishment_history")
    public ModelAndView getReplenishmentHistory(HttpSession session) {
        ModelAndView getReplenishmentHistoryPage = new ModelAndView("refillHistory");
        Account account = (Account) session.getAttribute("accounts");
        List<Replenishment> userReplenishmentHistory = replenishmentRepository.findByAccountId(account.getAccount_id());
        getReplenishmentHistoryPage.addObject("refill", userReplenishmentHistory);
        return getReplenishmentHistoryPage;
    }
}
