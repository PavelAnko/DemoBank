package com.demo_banking.service;

import com.demo_banking.auxiliary_methods.CurrencyExchange;
import com.demo_banking.models.Account;
import com.demo_banking.models.User;
import com.demo_banking.repository.AccountRepository;
import com.demo_banking.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;


@Service
public class MoneyTransfer {
    AccountRepository accountRepository;
    UserRepository userRepository;
    SaveToDBService saveToDataBase;

    public MoneyTransfer(AccountRepository accountRepository, UserRepository userRepository, SaveToDBService saveToDataBase) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.saveToDataBase = saveToDataBase;
    }

    public String moneyTransferService(String selectTransferCard, String recipientCard, String transferAmount, HttpSession session, Model model) {
        BigDecimal transferAmountDecimal = new BigDecimal(transferAmount);
        BigDecimal accountBalance = null;
        Account account = (Account) session.getAttribute("accounts");
        User user = (User) session.getAttribute("users");
        String listed_on, mainListed_on = "";
        if (selectTransferCard.equalsIgnoreCase("usd_card")) {
            accountBalance = account.getBalance_usd();

            if (accountBalance != null && accountBalance.compareTo(transferAmountDecimal) >= 0) {
/*                Account recipientAccount;*/

                if (accountRepository.findByUsdCardNumber(recipientCard) != null) {
                    listed_on = "USD";
                    mainListed_on = "USD";
                    transferMethod1(recipientCard, account, accountBalance, transferAmountDecimal, user, listed_on, mainListed_on);
                   /* recipientAccount = accountRepository.findByUsdCardNumber(recipientCard);
                    account.setBalance_usd(accountBalance.subtract(transferAmountDecimal));
                    accountRepository.save(account);

                    BigDecimal recipientBalance = recipientAccount.getBalance_usd();
                    recipientAccount.setBalance_usd(recipientBalance.add(transferAmountDecimal));
                    accountRepository.save(recipientAccount);

                    String recipientFirsName = userRepository.findFirstNameById(recipientAccount.getUser_id());
                    String recipientLastName = userRepository.findLastNameById(recipientAccount.getUser_id());

                    saveToDataBase.saveTransact(account, recipientAccount, "USD", transferAmountDecimal, recipientFirsName, recipientLastName);

                    listed_on = "USD";
                    saveToDataBase.saveRefill(user, recipientAccount, "USD", listed_on, transferAmountDecimal);*/
                } else if (accountRepository.findByUahCardNumber(recipientCard) != null) {
                    listed_on = "UAH";
                    mainListed_on = "USD";
                    transferMethod2(recipientCard, account, accountBalance, transferAmountDecimal, user, listed_on, mainListed_on);
                    /*recipientAccount = accountRepository.findByUahCardNumber(recipientCard);
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
                    saveToDataBase.saveRefill(user, recipientAccount, "USD", listed_on, transferAmountDecimal);*/
                } else {
                    exceptionElse(transferAmountDecimal, "USD", model, account);
                }
            } else {
                exceptionElse(transferAmountDecimal, "USD", model, account);
            }
        } else if (selectTransferCard.equalsIgnoreCase("uah_card")) {
            accountBalance = account.getBalance_uah();

            if (accountBalance != null && accountBalance.compareTo(transferAmountDecimal) >= 0) {
/*                Account recipientAccount;*/

                if (accountRepository.findByUsdCardNumber(recipientCard) != null) {
                    listed_on = "USD";
                    mainListed_on = "UAH";
                    transferMethod2(recipientCard, account, accountBalance, transferAmountDecimal, user, listed_on, mainListed_on);
/*                    recipientAccount = accountRepository.findByUsdCardNumber(recipientCard);
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
                    saveToDataBase.saveRefill(user, recipientAccount, "UAH", listed_on, transferAmountDecimal);*/
                } else if (accountRepository.findByUahCardNumber(recipientCard) != null) {
                    listed_on = "UAH";
                    mainListed_on = "UAH";
                    transferMethod1(recipientCard, account, accountBalance, transferAmountDecimal, user, listed_on, mainListed_on);
                    /*recipientAccount = accountRepository.findByUahCardNumber(recipientCard);
                    account.setBalance_uah(accountBalance.subtract(transferAmountDecimal));
                    accountRepository.save(account);

                    BigDecimal recipientBalance = recipientAccount.getBalance_uah();
                    recipientAccount.setBalance_uah(recipientBalance.add(transferAmountDecimal));
                    accountRepository.save(recipientAccount);

                    String recipientFirsName = userRepository.findFirstNameById(recipientAccount.getUser_id());
                    String recipientLastName = userRepository.findLastNameById(recipientAccount.getUser_id());

                    saveToDataBase.saveTransact(account, recipientAccount, "UAH", transferAmountDecimal, recipientFirsName, recipientLastName);

                    listed_on = "UAH";
                    saveToDataBase.saveRefill(user, recipientAccount, "UAH", listed_on, transferAmountDecimal);*/
                } else {
                    exceptionElse(transferAmountDecimal, "UAH", model, account);
                }
            } else {
                exceptionElse(transferAmountDecimal, "UAH", model, account);
            }
        }
        return "redirect:/app/dashboard";
    }

    public String exceptionElse(BigDecimal transferAmountDecimal, String currency, Model model, Account account) {
        saveToDataBase.saveFailedTransact(account, currency, transferAmountDecimal);
        model.addAttribute("error", "There are insufficient funds in the account");
        model.addAttribute("showError", true);
        saveToDataBase.addAccountBalancesToModel(model, account);
        saveToDataBase.addAccountCardNumber(model, account);
        return "dashboard";
    }

    public void transferMethod1(String recipientCard, Account account, BigDecimal accountBalance, BigDecimal transferAmountDecimal, User user, String listed_on, String mainListed_on) {
        Account recipientAccount = accountRepository.findByUsdCardNumber(recipientCard);

        if (mainListed_on == "USD") {
            account.setBalance_usd(accountBalance.subtract(transferAmountDecimal));
            accountRepository.save(account);

            BigDecimal recipientBalance = recipientAccount.getBalance_usd();
            recipientAccount.setBalance_usd(recipientBalance.add(transferAmountDecimal));
        } else {
            account.setBalance_uah(accountBalance.subtract(transferAmountDecimal));
            accountRepository.save(account);

            BigDecimal recipientBalance = recipientAccount.getBalance_uah();
            recipientAccount.setBalance_uah(recipientBalance.add(transferAmountDecimal));
        }
        accountRepository.save(recipientAccount);

        String recipientFirsName = userRepository.findFirstNameById(recipientAccount.getUser_id());
        String recipientLastName = userRepository.findLastNameById(recipientAccount.getUser_id());

        saveToDataBase.saveTransact(account, recipientAccount, mainListed_on, transferAmountDecimal, recipientFirsName, recipientLastName);

        saveToDataBase.saveRefill(user, recipientAccount, mainListed_on, listed_on, transferAmountDecimal);
    }

    public void transferMethod2(String recipientCard, Account account, BigDecimal accountBalance, BigDecimal transferAmountDecimal, User user, String listed_on, String mainListed_on) {
        if (mainListed_on == "USD"){
            Account recipientAccount = accountRepository.findByUahCardNumber(recipientCard);
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

            saveToDataBase.saveTransact(account, recipientAccount, mainListed_on, transferAmountDecimal, recipientFirsName, recipientLastName);
            saveToDataBase.saveRefill(user, recipientAccount, mainListed_on, listed_on, transferAmountDecimal);
        }
        else{
            Account recipientAccount = accountRepository.findByUsdCardNumber(recipientCard);
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

            saveToDataBase.saveTransact(account, recipientAccount, mainListed_on, transferAmountDecimal, recipientFirsName, recipientLastName);
            saveToDataBase.saveRefill(user, recipientAccount, mainListed_on, listed_on, transferAmountDecimal);
        }

    }
}