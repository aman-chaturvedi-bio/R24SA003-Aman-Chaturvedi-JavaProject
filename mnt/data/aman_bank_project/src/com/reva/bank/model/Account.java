package com.reva.bank.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    public static final double MIN_OPENING_BALANCE = 1000.00;
    protected static int accountCount;

    private String accountNumber;
    private Customer customer;
    private double balance;
    private final List<Transaction> transactions;

    public Account() {
        this("AC000", new Customer(), MIN_OPENING_BALANCE);
    }

    public Account(String accountNumber, Customer customer, double balance) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        accountCount++;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public static int getAccountCount() {
        return accountCount;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            return;
        }
        balance += amount;
        transactions.add(new Transaction(TransactionId.next(), TransactionType.DEPOSIT, amount));
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance || !canWithdraw(amount)) {
            return false;
        }
        balance -= amount;
        transactions.add(new Transaction(TransactionId.next(), TransactionType.WITHDRAWAL, amount));
        return true;
    }

    public void deposit(int amount) {
        deposit((double) amount);
    }

    protected boolean canWithdraw(double amount) {
        return true;
    }

    public abstract AccountType getAccountType();
    public abstract double calculateInterest();

    public final String getAccountSummary() {
        return String.format("%-12s %-10s %-20s Rs.%12.2f", accountNumber, getAccountType(), customer.getName(), balance);
    }

    @Override
    public String toString() {
        return getAccountSummary();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;
        return accountNumber.equalsIgnoreCase(other.accountNumber);
    }
}
