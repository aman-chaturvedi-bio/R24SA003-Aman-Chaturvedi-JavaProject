package com.reva.bank.model;

// Final prevents extension of this utility class.
public final class TransactionId {
    private static int counter = 1000;

    private TransactionId() {
    }

    public static String next() {
        return "T" + counter++;
    }
}
