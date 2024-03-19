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
    private Long replenishment_id;
    private Long refill_id_user;
    private Long account_id;
    private String replenishment_type;
    private double amount;
    private String source;
    private String listed_on;
    private LocalDateTime created_at;
    private String first_name;
    private String last_name;

    public Long getRefill_id_user() {
        return refill_id_user;
    }

    public void setRefill_id_user(Long refill_id_user) {
        this.refill_id_user = refill_id_user;
    }

    public String getListed_on() {
        return listed_on;
    }

    public void setListed_on(String listed_on) {
        this.listed_on = listed_on;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getReplenishment_id() {
        return replenishment_id;
    }

    public void setReplenishment_id(Long replenishment_id) {
        this.replenishment_id = replenishment_id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
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


    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
