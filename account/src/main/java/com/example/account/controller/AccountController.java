package com.example.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.account.model.Account;
import com.example.account.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin() // open for all port
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAccounts() {
        try {
            return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account{acc_number}")
    public ResponseEntity<Account> getAccountById(@PathVariable("acc_number") String id) {
        try {
            System.out.println(id);
            Account account = getAccId(id);

            if (account != null) {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/account")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        Account existingAcc = getAccId(account.getAcc_number());

        if (existingAcc != null) {
            existingAcc.setAccount_name(account.getAccount_name());
            existingAcc.setAccount_type(account.getAccount_type());
            existingAcc.setAcc_number(account.getAcc_number());
            existingAcc.setBalance(account.getBalance());
            existingAcc.setDate(account.getDate());

            return new ResponseEntity<>(accountRepository.save(existingAcc), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Account> newAccount(@RequestBody Account account) {
        Account newAccount = accountRepository
                .save(Account.builder()
                        .id(account.getId())
                        .account_name(account.getAccount_name())
                        .acc_number(account.getAcc_number())
                        .account_type(account.getAccount_type())
                        .balance(account.getBalance())
                        .date(account.getDate())
                        .build());
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
    }

    @DeleteMapping("/account")
    public ResponseEntity<Account> deleteAccount(@RequestBody Account account) {
        try {
            Account existingAccount = getAccId(account.getAcc_number());

            if (existingAccount != null) {
                accountRepository.deleteById(account.getAcc_number());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Account getAccId(String id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {
            return account.get();
        }
        return null;
    }

}