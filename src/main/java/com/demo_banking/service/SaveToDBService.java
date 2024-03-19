package com.demo_banking.service;

import com.demo_banking.models.Account;
import com.demo_banking.models.Replenishment;
import com.demo_banking.models.Transact;
import com.demo_banking.models.User;
import com.demo_banking.repository.ReplenishmentRepository;
import com.demo_banking.repository.TransactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class SaveToDBService {
/*    @Autowired
    private TransactRepository transactRepository;
    @Autowired
    private ReplenishmentRepository replenishmentRepository;*/
    TransactRepository transactRepository;
    ReplenishmentRepository replenishmentRepository;

    public SaveToDBService(TransactRepository transactRepository, ReplenishmentRepository replenishmentRepository) {
        this.transactRepository = transactRepository;
        this.replenishmentRepository = replenishmentRepository;
    }

    public void saveTransact(Account sender, Account recipient, String currency, BigDecimal amount, String recipientFirsName, String recipientLastName) {
        Transact transact = new Transact();
        transact.setAccount_id(sender.getAccount_id());
        transact.setTransaction_type("Transferred");
        transact.setAmount(amount.doubleValue());
        transact.setSource(currency);
        transact.setStatus("Success");
        transact.setCreated_at(LocalDateTime.now());
        transact.setFirst_name(recipientFirsName);
        transact.setLast_name(recipientLastName);
        transactRepository.save(transact);
    }

    public void saveRefill(User sender, Account recipient, String currency, String listed_on, BigDecimal amount) {
        Replenishment replenishment = new Replenishment();
        replenishment.setAccount_id(recipient.getAccount_id());
        replenishment.setReplenishment_type("Refilled");
        replenishment.setAmount(amount.doubleValue());
        replenishment.setSource(currency);
        replenishment.setListed_on(listed_on);
        replenishment.setCreated_at(LocalDateTime.now());
        replenishment.setFirst_name(sender.getFirst_name());
        replenishment.setLast_name(sender.getLast_name());
        replenishmentRepository.save(replenishment);
    }

    public void saveFailedTransact(Account sender, String currency, BigDecimal amount) {
        Transact transact = new Transact();
        transact.setAccount_id(sender.getAccount_id());
        transact.setTransaction_type("Not Transferred");
        transact.setAmount(amount.doubleValue());
        transact.setSource(currency);
        transact.setStatus("False");
        transact.setCreated_at(LocalDateTime.now());
        transact.setFirst_name("Error");
        transact.setLast_name("Transferred");
        transactRepository.save(transact);
    }

    public void addAccountCardNumber(Model model, Account account){
        model.addAttribute("uahCardNumber", account.getAccount_uah_number());
        model.addAttribute("usdCardNumber", account.getAccount_usd_number());
    }
    public void addAccountBalancesToModel(Model model, Account account) {
        model.addAttribute("totalUsdBalance", account.getBalance_usd());
        model.addAttribute("totalUahBalance", account.getBalance_uah());
    }

    public void saveRefill(Account sender, User recipient, String currency, String listed_on, BigDecimal amount) {
        Replenishment replenishment = new Replenishment();
        replenishment.setAccount_id(sender.getAccount_id());
        replenishment.setReplenishment_type("Refilled");
        replenishment.setAmount(amount.doubleValue());
        replenishment.setSource(currency);
        replenishment.setListed_on(listed_on);
        replenishment.setCreated_at(LocalDateTime.now());
        replenishment.setFirst_name(recipient.getFirst_name());
        replenishment.setLast_name(recipient.getLast_name());
        replenishmentRepository.save(replenishment);
    }
}
