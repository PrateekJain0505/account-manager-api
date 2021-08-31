package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
}
