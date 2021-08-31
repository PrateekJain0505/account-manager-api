package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.dto.AccountDetails;
import com.acmebank.accountmanager.dto.TransferDetails;
import com.acmebank.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/{userId}/{accountNum}")
    public ResponseEntity getAccountBalance(@PathVariable("userId") Long userId, @PathVariable("accountNum") Long accountNumber){
        try{
            AccountDetails accountDetails = accountService.getAccountBalance(userId, accountNumber);
            return ResponseEntity.ok().body(accountDetails);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


    @PostMapping("/account/")
    public ResponseEntity transferBalance(@RequestBody TransferDetails transferDetails){
        try{
            AccountDetails accountDetails = accountService.transferMoneyFromOneAccountToAnother(transferDetails);
            return ResponseEntity.ok().body(accountDetails);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
