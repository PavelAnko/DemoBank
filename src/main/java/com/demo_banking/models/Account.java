package com.demo_banking.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    private Long user_id;
    private String user_email;
    private String account_usd_number;
    private String account_uah_number;
    private BigDecimal balance_usd = BigDecimal.valueOf(0.00);
    private BigDecimal balance_uah = BigDecimal.valueOf(0.00);

    public Long getAccount_id() { return account_id; }

    public void setAccount_id(Long account_id) { this.account_id = account_id; }

    public Long getUser_id() { return user_id; }

    public void setUser_id(Long user_id) { this.user_id = user_id; }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getAccount_usd_number() {
        return account_usd_number;
    }

    public void setAccount_usd_number(String account_usd_number) {
        this.account_usd_number = account_usd_number;
    }

    public String getAccount_uah_number() {
        return account_uah_number;
    }

    public void setAccount_uah_number(String account_uah_number) {
        this.account_uah_number = account_uah_number;
    }

    public BigDecimal getBalance_usd() {
        return balance_usd;
    }

    public void setBalance_usd(BigDecimal balance_usd) {
        this.balance_usd = balance_usd;
    }

    public BigDecimal getBalance_uah() {
        return balance_uah;
    }

    public void setBalance_uah(BigDecimal balance_uah) {
        this.balance_uah = balance_uah;
    }

}
