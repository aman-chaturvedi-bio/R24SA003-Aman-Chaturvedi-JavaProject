package com.reva.bank.service;

import com.reva.bank.model.Account;
import com.reva.bank.model.AccountType;
import com.reva.bank.model.CurrentAccount;
import com.reva.bank.model.Customer;
import com.reva.bank.model.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

public class BankService implements Payable {
    private final List<Customer> customers = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account[] getAccountArray() {
        return accounts.toArray(new Account[0]);
    }

    public Customer findCustomer(String id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().isBlank()) {
                continue;
            }
            if (customer.getCustomerId().equalsIgnoreCase(id.trim())) {
                return customer;
            }
        }
        return null;
    }

    public Account findAccount(String number) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equalsIgnoreCase(number.trim())) {
                return account;
            }
        }
        return null;
    }

    public Account createAccount(String number, Customer customer, AccountType type, double openingBalance) {
        Account account;
        switch (type) {
            case SAVINGS:
                account = new SavingsAccount(number, customer, openingBalance, 25000.00);
                break;
            case CURRENT:
                account = new CurrentAccount(number, customer, openingBalance);
                break;
            default:
                return null;
        }
        accounts.add(account);
        return account;
    }

    public boolean transfer(String fromNumber, String toNumber, double amount) {
        Account from = findAccount(fromNumber);
        Account to = findAccount(toNumber);
        if (from == null || to == null || from.equals(to) || amount <= 0) {
            return false;
        }
        if (!from.withdraw(amount)) {
            return false;
        }
        to.deposit(amount);
        return true;
    }

    @Override
    public double calculatePayableAmount(double amount) {
        return amount + (amount * 0.02);
    }
}
