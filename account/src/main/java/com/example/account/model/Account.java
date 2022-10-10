package com.example.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Account")

public class Account {
    @Column(name="id")
    Long id;

    @Column(name="account_type")
    String account_type;
    @Id
    @Column(name = "acc_number")
    String acc_number;

    @Column(name = "account_name")
    String account_name;

    @Column(name = "Balance")
    String balance;

    @Column(name = "date")
    Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String accType) {
        this.account_type = accType;
    }

    public String getAcc_number() {
        return acc_number;
    }

    public void setAcc_number(String num) {
        this.acc_number = num;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String name) {
        this.account_name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}