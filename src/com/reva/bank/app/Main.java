package com.reva.bank.app;

import com.reva.bank.model.Account;
import com.reva.bank.model.AccountType;
import com.reva.bank.model.Customer;
import com.reva.bank.model.Transaction;
import com.reva.bank.service.BankService;
import com.reva.bank.service.Payable;
import com.reva.bank.util.InputUtil;

import java.util.Scanner;

public class Main {
    private static final String BANK_NAME = "REVA National Bank";
    private static final Scanner scanner = new Scanner(System.in);
    private static final BankService bankService = new BankService();

    public static void main(String[] args) {
        seedData();
        runApplication();
    }

    private static void seedData() {
        Customer aman = new Customer("C101", "Aman Chaturvedi", "9876543210", "aman@example.com");
        Customer rohan = new Customer("C102", "Rohan Mehta", "9988776655", "rohan@example.com");
        bankService.addCustomer(aman);
        bankService.addCustomer(rohan);
        bankService.createAccount("SA1001", aman, AccountType.SAVINGS, 15000.00);
        bankService.createAccount("CA2001", rohan, AccountType.CURRENT, 25000.00);
    }

    private static void runApplication() {
        boolean running = true;
        while (running) {
            showHeader();
            showMenu();
            int choice = InputUtil.readInt(scanner, "Select an option: ");
            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    showAccounts();
                    break;
                case 4:
                    deposit();
                    break;
                case 5:
                    withdraw();
                    break;
                case 6:
                    transfer();
                    break;
                case 7:
                    showTransactions();
                    break;
                case 8:
                    showInterestOrCharge();
                    break;
                case 9:
                    showStatistics();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        System.out.println("\nThank you for using " + BANK_NAME + ".");
    }

    private static void showHeader() {
        System.out.println("\n============================================================");
        System.out.println("                 " + BANK_NAME);
        System.out.println("============================================================");
    }

    private static void showMenu() {
        System.out.println("1. Register Customer");
        System.out.println("2. Open Bank Account");
        System.out.println("3. View Accounts");
        System.out.println("4. Deposit Money");
        System.out.println("5. Withdraw Money");
        System.out.println("6. Transfer Money");
        System.out.println("7. View Transactions");
        System.out.println("8. Interest / Service Charge");
        System.out.println("9. System Statistics");
        System.out.println("0. Exit");
    }

    private static void createCustomer() {
        String id = InputUtil.readText(scanner, "Customer ID: ");
        String name = InputUtil.readText(scanner, "Full name: ");
        String phone = InputUtil.readText(scanner, "Phone: ");
        String email = InputUtil.readText(scanner, "Email: ");
        if (name.isEmpty() || id.isEmpty()) {
            System.out.println("Customer ID and name are required.");
            return;
        }
        bankService.addCustomer(new Customer(id, name, phone, email));
        System.out.println("Customer registered successfully.");
    }

    private static void createAccount() {
        String customerId = InputUtil.readText(scanner, "Customer ID: ");
        Customer customer = bankService.findCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        String number = InputUtil.readText(scanner, "Account number: ");
        if (bankService.findAccount(number) != null) {
            System.out.println("Account number already exists.");
            return;
        }
        System.out.println("1. Savings");
        System.out.println("2. Current");
        int option = InputUtil.readInt(scanner, "Account type: ");
        AccountType type;
        if (option == 1) {
            type = AccountType.SAVINGS;
        } else if (option == 2) {
            type = AccountType.CURRENT;
        } else {
            System.out.println("Invalid account type.");
            return;
        }
        double openingBalance = InputUtil.readDouble(scanner, "Opening balance: ");
        if (openingBalance < Account.MIN_OPENING_BALANCE) {
            System.out.printf("Minimum opening balance is Rs.%.2f%n", Account.MIN_OPENING_BALANCE);
            return;
        }
        bankService.createAccount(number, customer, type, openingBalance);
        System.out.println("Account opened successfully.");
    }

    private static void showAccounts() {
        System.out.println("\nACCOUNT NUMBER TYPE       CUSTOMER             BALANCE");
        System.out.println("------------------------------------------------------------");
        for (Account account : bankService.getAccounts()) {
            System.out.println(account);
        }
    }

    private static void deposit() {
        Account account = findAccountFromUser();
        if (account == null) {
            return;
        }
        double amount = InputUtil.readDouble(scanner, "Deposit amount: ");
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        account.deposit(amount);
        System.out.printf("Deposit successful. New balance: Rs.%.2f%n", account.getBalance());
    }

    private static void withdraw() {
        Account account = findAccountFromUser();
        if (account == null) {
            return;
        }
        double amount = InputUtil.readDouble(scanner, "Withdrawal amount: ");
        if (account.withdraw(amount)) {
            System.out.printf("Withdrawal successful. New balance: Rs.%.2f%n", account.getBalance());
        } else {
            System.out.println("Withdrawal rejected. Check balance and account rules.");
        }
    }

    private static void transfer() {
        String from = InputUtil.readText(scanner, "From account: ");
        String to = InputUtil.readText(scanner, "To account: ");
        double amount = InputUtil.readDouble(scanner, "Transfer amount: ");
        if (bankService.transfer(from, to, amount)) {
            System.out.println("Transfer completed successfully.");
        } else {
            System.out.println("Transfer failed. Verify account numbers, amount and balance.");
        }
    }

    private static void showTransactions() {
        Account account = findAccountFromUser();
        if (account == null) {
            return;
        }
        System.out.println("\nID       TYPE         AMOUNT        TIME");
        System.out.println("------------------------------------------------------------");
        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction transaction : account.getTransactions()) {
            System.out.println(transaction);
        }
    }

    private static void showInterestOrCharge() {
        Account account = findAccountFromUser();
        if (account == null) {
            return;
        }
        double value = account.calculateInterest();
        if (value >= 0) {
            System.out.printf("Estimated interest: Rs.%.2f%n", value);
        } else {
            System.out.printf("Applicable service charge: Rs.%.2f%n", Math.abs(value));
        }
    }

    private static void showStatistics() {
        int customerCount = Customer.getCustomerCount();
        int accountCount = Account.getAccountCount();
        double totalBalance = 0;
        for (Account account : bankService.getAccountArray()) {
            totalBalance += account.getBalance();
        }
        int roundedBalance = (int) totalBalance;
        double serviceEstimate = ((Payable) bankService).calculatePayableAmount(1000);
        System.out.println("\nSystem Statistics");
        System.out.println("-----------------");
        System.out.println("Customers created : " + customerCount);
        System.out.println("Accounts created  : " + accountCount);
        System.out.printf("Total balance     : Rs.%.2f%n", totalBalance);
        System.out.println("Rounded balance   : Rs." + roundedBalance);
        System.out.printf("Payable example   : Rs.%.2f%n", serviceEstimate);
        System.out.println("Operator example  : 1000 + 2% of 1000 = " + serviceEstimate);
    }

    private static Account findAccountFromUser() {
        String number = InputUtil.readText(scanner, "Account number: ");
        Account account = bankService.findAccount(number);
        if (account == null) {
            System.out.println("Account not found.");
        }
        return account;
    }
}
