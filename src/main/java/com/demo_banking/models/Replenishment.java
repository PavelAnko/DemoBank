package com.demo_banking.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Replenishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replenishment_id;
    private int account_id;
    private String replenishment_type;
    private double amount;
    private String source;
    private String status;
    private LocalDateTime created_at;

    public int getReplenishment_id() {
        return replenishment_id;
    }

    public void setReplenishment_id(int replenishment_id) {
        this.replenishment_id = replenishment_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getReplenishment_type() {
        return replenishment_type;
    }

    public void setReplenishment_type(String replenishment_type) {
        this.replenishment_type = replenishment_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
