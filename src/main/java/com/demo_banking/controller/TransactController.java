package com.demo_banking.controller;

import com.demo_banking.auxiliary_methods.CurrencyExchange;
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
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SaveToDBService saveToDataBase;

    @PostMapping("/app/dashboard/money_transfer")
    @Transactional
    public String moneyTransfer(@RequestParam("select_card") String selectTransferCard,
                                @RequestParam("recipient_card") String recipientCard,
                                @RequestParam("transfer_amount") String transferAmount,
                                HttpSession session, Model model) {
        BigDecimal transferAmountDecimal = new BigDecimal(transferAmount);
        BigDecimal accountBalance = null;
        Account account = (Account) session.getAttribute("accounts");
        User user = (User) session.getAttribute("users");
        String listed_on ="";
        if (selectTransferCard.equalsIgnoreCase("usd_card")) {
            accountBalance = account.getBalance_usd();

            if (accountBalance != null && accountBalance.compareTo(transferAmountDecimal) >= 0) {
                Account recipientAccount;

                if (accountRepository.findByUsdCardNumber(recipientCard) != null) {

                    recipientAccount = accountRepository.findByUsdCardNumber(recipientCard);
                    account.setBalance_usd(accountBalance.subtract(transferAmountDecimal));
                    accountRepository.save(account);

                    BigDecimal recipientBalance = recipientAccount.getBalance_usd();
                    recipientAccount.setBalance_usd(recipientBalance.add(transferAmountDecimal));
                    accountRepository.save(recipientAccount);

                    String recipientFirsName = userRepository.findFirstNameById(recipientAccount.getUser_id());
                    String recipientLastName = userRepository.findLastNameById(recipientAccount.getUser_id());

                    saveToDataBase.saveTransact(account, recipientAccount, "USD", transferAmountDecimal, recipientFirsName, recipientLastName);

                    listed_on = "USD";
                    saveToDataBase.saveRefill(user, recipientAccount, "USD", listed_on, transferAmountDecimal);
                } else if (accountRepository.findByUahCardNumber(recipientCard) != null) {
                    recipientAccount = accountRepository.findByUahCardNumber(recipientCard);
                    BigDecimal transferAmountUsd = transferAmountDecimal;
                    BigDecimal usdToUahExchangeRate = CurrencyExchange.getUsdToUahExchangeRate();
                    BigDecimal transferAmountUah = transferAmountUsd.multiply(usdToUahExchangeRate);

                    account.setBalance_usd(accountBalance.subtract(transferAmountUsd));
                    accountRepository.save(account);

                    BigDecimal recipientBalance = recipientAccount.getBalance_uah();
                    recipientAccount.setBalance_uah(recipientBalance.add(transferAmountUah));
                    accountRepository.save(recipientAccount);

                    String recipientFirsName = userRepository.findFirstNameById(recipientAccount.getUser_id());
                    String recipientLastName = userRepository.findLastNameById(recipientAccount.getUser_id());

                    saveToDataBase.saveTransact(account, recipientAccount, "USD", transferAmountDecimal, recipientFirsName, recipientLastName);

                    listed_on = "UAH";
                    saveToDataBase.saveRefill(user, recipientAccount, "USD", listed_on, transferAmountDecimal);
                } else {
                    saveToDataBase.saveFailedTransact(account, "USD", transferAmountDecimal);
                    model.addAttribute("error", "The card is incorrect or does not exist");
                    model.addAttribute("showError", true);
                    saveToDataBase.addAccountBalancesToModel(model, account);
                    saveToDataBase.addAccountCardNumber(model,account);
                    return "dashboard";
                }
            } else {
                saveToDataBase.saveFailedTransact(account, "USD", transferAmountDecimal);
                model.addAttribute("error", "There are insufficient funds in the account");
                model.addAttribute("showError", true);
                saveToDataBase.addAccountBalancesToModel(model, account);
                saveToDataBase.addAccountCardNumber(model,account);
                return "dashboard";

            }
        } else if (selectTransferCard.equalsIgnoreCase("uah_card")) {
            accountBalance = account.getBalance_uah();

            if (accountBalance != null && accountBalance.compareTo(transferAmountDecimal) >= 0) {
                Account recipientAccount;

                if (accountRepository.findByUsdCardNumber(recipientCard) != null) {
                    recipientAccount = accountRepository.findByUsdCardNumber(recipientCard);
                    BigDecimal transferAmountUah = transferAmountDecimal;
                    BigDecimal uahToUsdExchangeRate = CurrencyExchange.getUahToUsdExchangeRate();
                    BigDecimal transferAmountUsd = transferAmountUah.multiply(uahToUsdExchangeRate);

                    account.setBalance_uah(accountBalance.subtract(transferAmountUah));
                    accountRepository.save(account);

                    BigDecimal recipientBalance = recipientAccount.getBalance_usd();
                    recipientAccount.setBalance_usd(recipientBalance.add(transferAmountUsd));
                    accountRepository.save(recipientAccount);

                    String recipientFirsName = userRepository.findFirstNameById(recipientAccount.getUser_id());
                    String recipientLastName = userRepository.findLastNameById(recipientAccount.getUser_id());

                    saveToDataBase.saveTransact(account, recipientAccount, "UAH", transferAmountDecimal, recipientFirsName, recipientLastName);

                    listed_on = "USD";
                    saveToDataBase.saveRefill(user, recipientAccount, "UAH",listed_on, transferAmountDecimal);
                } else if (accountRepository.findByUahCardNumber(recipientCard) != null) {
                    recipientAccount = accountRepository.findByUahCardNumber(recipientCard);
                    account.setBalance_uah(accountBalance.subtract(transferAmountDecimal));
                    accountRepository.save(account);

                    BigDecimal recipientBalance = recipientAccount.getBalance_uah();
                    recipientAccount.setBalance_uah(recipientBalance.add(transferAmountDecimal));
                    accountRepository.save(recipientAccount);

                    String recipientFirsName = userRepository.findFirstNameById(recipientAccount.getUser_id());
                    String recipientLastName = userRepository.findLastNameById(recipientAccount.getUser_id());

                    saveToDataBase.saveTransact(account, recipientAccount, "UAH", transferAmountDecimal, recipientFirsName, recipientLastName);

                    listed_on = "UAH";
                    saveToDataBase.saveRefill(user, recipientAccount, "UAH",listed_on, transferAmountDecimal);
                } else {
                    saveToDataBase.saveFailedTransact(account, "UAH", transferAmountDecimal);
                    model.addAttribute("error", "The card is incorrect or does not exist");
                    model.addAttribute("showError", true);
                    saveToDataBase.addAccountBalancesToModel(model, account);
                    saveToDataBase.addAccountCardNumber(model,account);
                    return "dashboard";
                }
            } else {
                saveToDataBase.saveFailedTransact(account, "UAH", transferAmountDecimal);
                model.addAttribute("error", "There are insufficient funds in the account");
                model.addAttribute("showError", true);
                saveToDataBase.addAccountBalancesToModel(model, account);
                saveToDataBase.addAccountCardNumber(model,account);
                return "dashboard";
            }
        }
        return "redirect:/app/dashboard";
    }
}


