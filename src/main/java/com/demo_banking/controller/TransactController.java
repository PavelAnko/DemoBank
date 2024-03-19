package com.demo_banking.controller;

import com.demo_banking.auxiliary_methods.CurrencyExchange;
import com.demo_banking.service.MoneyTransfer;
import com.demo_banking.service.SaveToDBService;
import com.demo_banking.models.Account;
import com.demo_banking.models.User;
import com.demo_banking.repository.AccountRepository;
import com.demo_banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Controller
public class TransactController {
//    @Autowired
//    private AccountRepository accountRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private SaveToDBService saveToDataBase;

    MoneyTransfer moneyTransfer;

    public TransactController(MoneyTransfer moneyTransfer) {
        this.moneyTransfer = moneyTransfer;
    }

    @PostMapping("/app/dashboard/money_transfer")
    @Transactional
    public String moneyTransfer(@RequestParam("select_card") String selectTransferCard,
                                @RequestParam("recipient_card") String recipientCard,
                                @RequestParam("transfer_amount") String transferAmount,
                                HttpSession session, Model model) {
        return moneyTransfer.moneyTransferService(selectTransferCard, recipientCard, transferAmount, session, model);
    }
}


