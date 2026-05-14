package com.hemant.banking.service;

import com.hemant.banking.entity.Account;
import com.hemant.banking.entity.Transaction;
import com.hemant.banking.repository.AccountRepository;
import com.hemant.banking.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account deposit(String accountNumber, Double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        transactionRepository.save(new Transaction(
                null,
                accountNumber,
                "DEPOSIT",
                amount,
                LocalDateTime.now()
        ));

        return account;
    }

    public Account withdraw(String accountNumber, Double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        transactionRepository.save(new Transaction(
                null,
                accountNumber,
                "WITHDRAW",
                amount,
                LocalDateTime.now()
        ));

        return account;
    }
}
