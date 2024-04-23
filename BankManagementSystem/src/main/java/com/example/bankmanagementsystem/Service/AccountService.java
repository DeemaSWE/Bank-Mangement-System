package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AccountRepository;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public void createAccount(Integer userId, Account account) {
        Customer customer = customerRepository.findCustomerById(userId);

        account.setCustomer(customer);

        accountRepository.save(account);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public void updateAccount(Integer userId, Integer accountId, Account updatedAccount) {
        Account account = accountRepository.findAccountById(accountId);

        if(account == null)
            throw new ApiException("Account not found");

        if(account.getCustomer().getId() != userId)
            throw new ApiException("You are not authorized to update this account");

        account.setAccountNumber(updatedAccount.getAccountNumber());
        account.setBalance(updatedAccount.getBalance());

        accountRepository.save(account);
    }

    public void deleteAccount(Integer userId, Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        User user = authRepository.findUserById(userId);

        if(account == null)
            throw new ApiException("Account not found");

        if(user.getRole().equals("CUSTOMER") && account.getCustomer().getId() != userId)
            throw new ApiException("You are not authorized to delete this account");

        accountRepository.delete(account);
    }

    public void activateAccount(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);

        if(account == null)
            throw new ApiException("Account not found");

        account.setIsActive(true);

        accountRepository.save(account);
    }

    public Account getAccount(Integer userId, Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);

        if(account == null)
            throw new ApiException("Account not found");

        if(account.getCustomer().getId() != userId)
            throw new ApiException("You are not authorized to view this account");

        return account;
    }

    public void depositMoney(Integer userId, Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);

        if(account == null)
            throw new ApiException("Account not found");

        if(account.getCustomer().getId() != userId)
            throw new ApiException("You are not authorized to deposit money to this account");

        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);
    }

    public void withdrawMoney(Integer userId, Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);

        if(account == null)
            throw new ApiException("Account not found");

        if(account.getCustomer().getId() != userId)
            throw new ApiException("You are not authorized to withdraw money from this account");

        if(account.getBalance() < amount)
            throw new ApiException("Insufficient balance");

        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);
    }

    public void transferMoney(Integer userId, Integer accountId, Integer targetAccountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        Account targetAccount = accountRepository.findAccountById(targetAccountId);

        if(account == null || targetAccount == null)
            throw new ApiException("Account not found");

        if(account.getCustomer().getId() != userId)
            throw new ApiException("You are not authorized to transfer money from this account");

        if(account.getBalance() < amount)
            throw new ApiException("Insufficient balance");

        account.setBalance(account.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        accountRepository.save(account);
        accountRepository.save(targetAccount);
    }

    public void blockAccount(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);

        if(account == null)
            throw new ApiException("Account not found");

        account.setIsActive(false);

        accountRepository.save(account);
    }
}
