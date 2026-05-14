package com.hemant.banking.controller;

import com.hemant.banking.entity.Account;
import com.hemant.banking.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PostMapping("/{accountNumber}/deposit")
    public Account deposit(@PathVariable String accountNumber,
                           @RequestParam Double amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public Account withdraw(@PathVariable String accountNumber,
                            @RequestParam Double amount) {
        return accountService.withdraw(accountNumber, amount);
    }
}
