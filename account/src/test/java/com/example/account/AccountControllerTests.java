package com.example.account;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.account.repository.AccountRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.account.model.Account;
import com.example.account.controller.AccountController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTests {

    @MockBean
    AccountRepository accountRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    public void test_allAccounts_oneEntry_StatusOK() throws Exception {
        Account account = new Account(1L, "Term Investment", "2345", "jed opie", "400", Date.valueOf("2022-10-10"));
        List<Account> accounts = Arrays.asList(account);

        when(accountRepository.findAll()).thenReturn(accounts);

        mockMvc.perform(get("/account/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].account_name", Matchers.is("jed opie")));
    }

    @Test
    @Order(2)
    public void test_accountById_correctId() throws Exception {
        Account account = new Account(1L, "Term Investment", "2345", "jed opie", "400", Date.valueOf("2022-10-10"));
        List<Account> accounts = Arrays.asList(account);

        Optional<Account> acc = Optional.of(account);
        String id = "2345";

        when(accountRepository.findById(id)).thenReturn(acc);
        mockMvc.perform(get("/account/account2345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    @Order(3)
    public void test_accountById_wrongId() throws Exception {
        Account account = new Account(1L, "Term Investment", "2345", "jed opie", "400", Date.valueOf("2022-10-10"));
        List<Account> accounts = Arrays.asList(account);

        Optional<Account> acc = Optional.empty();
        String id = "123";

        when(accountRepository.findById(id)).thenReturn(acc);
        mockMvc.perform(get("/account/account123"))
                .andExpect(status().isNotFound());
    }




}