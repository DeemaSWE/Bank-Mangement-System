package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // Customer
    @PostMapping("/create")
    public ResponseEntity createAccount(@AuthenticationPrincipal User user, @RequestBody @Valid Account account){
        accountService.createAccount(user.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account created successfully"));
    }

    // Admin
    @GetMapping("/get-all")
    public ResponseEntity getAllAccounts(){
        return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }

    // Customer
    @PutMapping("/update/{accountId}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @RequestBody @Valid Account account){
        accountService.updateAccount(user.getId(), accountId, account);
        return ResponseEntity.status(200).body(new ApiResponse("Account updated successfully"));
    }

    // Customer and Admin
    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId){
        accountService.deleteAccount(user.getId(), accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account deleted successfully"));
    }

    // Admin
    @PutMapping("/activate/{accountId}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId){
        accountService.activateAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated successfully"));
    }

    // Customer
    @GetMapping("/view/{accountId}")
    public ResponseEntity getAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId){
        return ResponseEntity.status(200).body(accountService.getAccount(user.getId(), accountId));
    }

    // Customer
    @GetMapping("/get-my-accounts")
    public ResponseEntity getMyAccounts(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(accountService.getMyAccounts(user.getId()));
    }


    // Customer
    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity depositMoney(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @PathVariable Double amount){
        accountService.depositMoney(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Money deposited successfully"));
    }

    // Customer
    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity withdrawMoney(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @PathVariable Double amount){
        accountService.withdrawMoney(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Money withdrawn successfully"));
    }

    // Customer
    @PutMapping("/transfer/{fromAccountId}/{toAccountId}/{amount}")
    public ResponseEntity transferMoney(@AuthenticationPrincipal User user, @PathVariable Integer fromAccountId, @PathVariable Integer toAccountId, @PathVariable Double amount){
        accountService.transferMoney(user.getId(), fromAccountId, toAccountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Money transferred successfully"));
    }

    // Admin
    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId){
        accountService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked successfully"));
    }


}
