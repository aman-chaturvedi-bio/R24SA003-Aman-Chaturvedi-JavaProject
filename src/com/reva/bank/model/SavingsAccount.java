package com.reva.bank.model;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04;
    private double withdrawalLimit;

    public SavingsAccount() {
        this("SA000", new Customer(), MIN_OPENING_BALANCE, 25000.00);
    }

    public SavingsAccount(String accountNumber, Customer customer, double balance, double withdrawalLimit) {
        super(accountNumber, customer, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    @Override
    protected boolean canWithdraw(double amount) {
        return amount <= withdrawalLimit;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }

    @Override
    public double calculateInterest() {
        return super.getBalance() * INTEREST_RATE;
    }
}
