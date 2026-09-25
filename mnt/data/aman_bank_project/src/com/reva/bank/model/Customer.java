package com.reva.bank.model;

public class Customer extends Person {
    private String customerId;
    private static int customerCount;

    public Customer() {
        this("C000", "Unknown", "Not Provided", "Not Provided");
    }

    public Customer(String customerId, String name, String phone, String email) {
        super(name, phone, email);
        this.customerId = customerId;
        customerCount++;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public static int getCustomerCount() {
        return customerCount;
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public String toString() {
        return String.format("%-8s %-22s %-15s %-28s", customerId, getName(), getPhone(), getEmail());
    }
}
