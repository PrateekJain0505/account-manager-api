package com.acmebank.accountmanager.dto;

public class TransferDetails {

    private Long accountNumberTo;

    private Long userIdFrom;

    private Long accountNumberFrom;

    private Double transferAmount;

    public Long getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(Long accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }

    public Long getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public void setAccountNumberFrom(Long accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
    }

    public Long getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(Long userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }
}
