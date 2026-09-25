package com.reva.bank.model;

public class CurrentAccount extends Account {
    private static final double MIN_BALANCE = 5000.00;
    private static final double SERVICE_CHARGE = 250.00;

    public CurrentAccount() {
        this("CA000", new Customer(), 10000.00);
    }

    public CurrentAccount(String accountNumber, Customer customer, double balance) {
        super(accountNumber, customer, balance);
    }

    @Override
    protected boolean canWithdraw(double amount) {
        return getBalance() - amount >= MIN_BALANCE;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CURRENT;
    }

    @Override
    public double calculateInterest() {
        return -SERVICE_CHARGE;
    }
}
