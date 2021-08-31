package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.dto.AccountDetails;
import com.acmebank.accountmanager.dto.TransferDetails;
import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.TransactionHistory;
import com.acmebank.accountmanager.repository.AccountRepository;
import com.acmebank.accountmanager.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    public AccountDetails getAccountBalance(Long userId, Long accountNumber) throws Exception {

        Account accountFromDb = accountRepository.findByUserIdAndAccountId(userId, accountNumber);
        if(accountFromDb == null){
            throw new Exception("No Account" + accountNumber +" exist for User " + userId);
        }
        return convertAccountDbObjectToDTO(accountFromDb);
    }

    public AccountDetails transferMoneyFromOneAccountToAnother(TransferDetails transferDetails) throws Exception {

        Account accountFrom = accountRepository.findByUserIdAndAccountId(transferDetails.getUserIdFrom(), transferDetails.getAccountNumberFrom());

        if(accountFrom == null){
            throw new Exception("No Account" + transferDetails.getUserIdFrom() +" exist for User " + transferDetails.getAccountNumberFrom());
        }

        Account accountTo = accountRepository.getById(transferDetails.getAccountNumberTo());

        if(accountTo == null){
            throw new Exception("No account with account number " + transferDetails.getAccountNumberTo() + " exist");
        }

        if(accountFrom.getAccountBalance() < transferDetails.getTransferAmount()){
            throw new Exception("Not enough account balance to transfer");
        }

        accountTo.setAccountBalance(accountTo.getAccountBalance() + transferDetails.getTransferAmount());

        accountTo = accountRepository.save(accountTo);

        accountFrom.setAccountBalance(accountFrom.getAccountBalance() - transferDetails.getTransferAmount());

        accountFrom = accountRepository.save(accountFrom);

        saveTransactionHistory(transferDetails, accountFrom, accountTo);

        return convertAccountDbObjectToDTO(accountFrom);
    }

    private void saveTransactionHistory(TransferDetails transferDetails, Account accountFrom, Account accountTo) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionDate(LocalDateTime.now());
        transactionHistory.setTransactionType("Balance Transfer");
        transactionHistory.setAccountIdFrom(accountFrom.getAccountId());
        transactionHistory.setUserIdFrom(accountFrom.getUserId());
        transactionHistory.setTransactionAmount(transferDetails.getTransferAmount());
        transactionHistory.setAccountIdTo(accountTo.getAccountId());
        transactionHistory.setUserIdTo(accountTo.getUserId());

        transactionHistoryRepository.save(transactionHistory);
    }

    private AccountDetails convertAccountDbObjectToDTO(Account accountFromDb){
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountId(accountFromDb.getAccountId());
        accountDetails.setAccountBalance(accountFromDb.getAccountBalance());
        accountDetails.setAccountCurrency(accountFromDb.getAccountCurrency());
        accountDetails.setUserId(accountFromDb.getUserId());

        return accountDetails;
    }
}
