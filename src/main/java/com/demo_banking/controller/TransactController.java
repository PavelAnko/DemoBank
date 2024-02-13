package com.demo_banking.controller;

import com.demo_banking.auxiliary_methods.CurrencyExchange;
import com.demo_banking.models.Account;
import com.demo_banking.models.Transact;
import com.demo_banking.repository.AccountRepository;
import com.demo_banking.repository.TransactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class TransactController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactRepository transactRepository;

    @PostMapping("/money_transfer")
    @Transactional
    public String moneyTransfer(@RequestParam("select_card") String selectTransferCard,
                                @RequestParam("recipient_card") String recipientCard,
                                @RequestParam("transfer_amount") String transferAmount,
                                HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        BigDecimal transferAmountDecimal = new BigDecimal(transferAmount);
        BigDecimal accountBalance = null;
        Account account = (Account) session.getAttribute("accounts");
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
                    saveTransact(account, recipientAccount, "USD", transferAmountDecimal);
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
                    saveTransact(account, recipientAccount, "USD", transferAmountDecimal);
                } else {
                    saveFailedTransact(account, "USD", transferAmountDecimal);
                    redirectAttributes.addFlashAttribute("error", "Recipient card not found");
                    return "redirect:/app/dashboard";
                }
            } else {
                saveFailedTransact(account, "USD", transferAmountDecimal);
                redirectAttributes.addFlashAttribute("error", "Insufficient funds");
                return "redirect:/app/dashboard";
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
                    saveTransact(account, recipientAccount, "UAH", transferAmountDecimal);
                } else if (accountRepository.findByUahCardNumber(recipientCard) != null) {
                    recipientAccount = accountRepository.findByUahCardNumber(recipientCard);
                    account.setBalance_uah(accountBalance.subtract(transferAmountDecimal));
                    accountRepository.save(account);
                    BigDecimal recipientBalance = recipientAccount.getBalance_uah();
                    recipientAccount.setBalance_uah(recipientBalance.add(transferAmountDecimal));
                    accountRepository.save(recipientAccount);
                    saveTransact(account, recipientAccount, "UAH", transferAmountDecimal);
                } else {
                    saveFailedTransact(account, "UAH", transferAmountDecimal);
                    return "redirect:/app/dashboard";
                }
            } else {
                saveFailedTransact(account, "UAH", transferAmountDecimal);
                return "redirect:/app/dashboard";
            }
        }
        session.setAttribute("accounts", accountRepository.findById(account.getAccount_id()).orElse(null));
        return "redirect:/app/dashboard";
    }

    private void saveTransact(Account sender, Account recipient, String currency, BigDecimal amount) {
        Transact transact = new Transact();
        transact.setAccount_id(sender.getAccount_id());
        transact.setTransaction_type("Transferred");
        transact.setAmount(amount.doubleValue());
        transact.setSource(currency);
        transact.setStatus("Success");
        transact.setCreated_at(LocalDateTime.now());
        transactRepository.save(transact);
    }

    private void saveFailedTransact(Account sender, String currency, BigDecimal amount) {
        Transact transact = new Transact();
        transact.setAccount_id(sender.getAccount_id());
        transact.setTransaction_type("Not Transferred");
        transact.setAmount(amount.doubleValue());
        transact.setSource(currency);
        transact.setStatus("False");
        transact.setCreated_at(LocalDateTime.now());
        transactRepository.save(transact);
    }
}




//@Controller
//public class TransactController {
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @PostMapping("/money_transfer")
//    @Transactional
//    public String moneyTransfer(@RequestParam ("select_card") String selectTransferCard,
//                                @RequestParam ("recipient_card") String recipientCard,
//                                @RequestParam("transfer_amount") String transferAmount,
//                                HttpSession session, Model model){
//        BigDecimal transferAmountDecimal = new BigDecimal(transferAmount);
//        BigDecimal accountBalance = null;
//        Account account = (Account) session.getAttribute("accounts");
//        if (selectTransferCard.equalsIgnoreCase("usd_card")) {
//            accountBalance = account.getBalance_usd();
//
//            if (accountBalance != null && accountBalance.compareTo(transferAmountDecimal)>=0){
//                Account recipientAccount;
//
//                if (accountRepository.findByUsdCardNumber(recipientCard) != null) {
//                    recipientAccount = accountRepository.findByUsdCardNumber(recipientCard);
//                    account.setBalance_usd(accountBalance.subtract(transferAmountDecimal));
//                    accountRepository.save(account);
//                    BigDecimal recipientBalance = recipientAccount.getBalance_usd();
//                    recipientAccount.setBalance_usd(recipientBalance.add(transferAmountDecimal));
//                    accountRepository.save(recipientAccount);
//                }
//
//                else if (accountRepository.findByUahCardNumber(recipientCard) != null) {
//                    recipientAccount = accountRepository.findByUahCardNumber(recipientCard);
//                    BigDecimal transferAmountUsd = transferAmountDecimal;
//                    BigDecimal usdToUahExchangeRate = CurrencyExchange.getUsdToUahExchangeRate();
//                    BigDecimal transferAmountUah = transferAmountUsd.multiply(usdToUahExchangeRate);
//
//                    account.setBalance_usd(accountBalance.subtract(transferAmountUsd));
//                    accountRepository.save(account);
//
//                    BigDecimal recipientBalance = recipientAccount.getBalance_uah();
//                    recipientAccount.setBalance_uah(recipientBalance.add(transferAmountUah));
//                    accountRepository.save(recipientAccount);
//                }
//
//                else
//                    model.addAttribute("recipient_card_not_exist_error", "Recipient's card does not exist.");
//
//            } else
//                model.addAttribute("insufficient_funds_error", "Insufficient funds.");
//
//
//        }else if (selectTransferCard.equalsIgnoreCase("uah_card")) {
//            accountBalance = account.getBalance_uah();
//
//            if (accountBalance != null && accountBalance.compareTo(transferAmountDecimal) >= 0) {
//                Account recipientAccount;
//
//                if (accountRepository.findByUsdCardNumber(recipientCard) != null) {
//                    recipientAccount = accountRepository.findByUsdCardNumber(recipientCard);
//                    BigDecimal transferAmountUah = transferAmountDecimal;
//                    BigDecimal uahToUsdExchangeRate = CurrencyExchange.getUahToUsdExchangeRate();
//                    BigDecimal transferAmountUsd = transferAmountUah.multiply(uahToUsdExchangeRate);
//
//                    account.setBalance_uah(accountBalance.subtract(transferAmountUah));
//                    accountRepository.save(account);
//
//                    BigDecimal recipientBalance = recipientAccount.getBalance_usd();
//                    recipientAccount.setBalance_usd(recipientBalance.add(transferAmountUsd));
//                    accountRepository.save(recipientAccount);
//                } else if (accountRepository.findByUahCardNumber(recipientCard) != null) {
//                    recipientAccount = accountRepository.findByUahCardNumber(recipientCard);
//                    account.setBalance_uah(accountBalance.subtract(transferAmountDecimal));
//                    accountRepository.save(account);
//                    BigDecimal recipientBalance = recipientAccount.getBalance_uah();
//                    recipientAccount.setBalance_uah(recipientBalance.add(transferAmountDecimal));
//                    accountRepository.save(recipientAccount);
//                } else
//                    model.addAttribute("insufficient_funds_error", "Insufficient funds.");
//            } else
//                model.addAttribute("insufficient_funds_error", "Insufficient funds.");
//        }
//        session.setAttribute("accounts", accountRepository.findById(account.getAccount_id()).orElse(null));
//        return "redirect:/app/dashboard";
//    }
//}

