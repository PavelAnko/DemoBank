package com.demo_banking.models;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private int account_id;
    private int user_id;
    private String user_email;
    private String account_usd_number;
    private String account_uah_number;
    private BigDecimal balance_usd = BigDecimal.valueOf(0.00);
    private BigDecimal balance_uah = BigDecimal.valueOf(0.00);

    public int getAccount_id() {
        return account_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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