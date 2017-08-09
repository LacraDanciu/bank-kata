package com.craft;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private Date transactionDate;
    private int amount;
    private TransactionType transactionType;

    public Transaction(Date transactionDate, int amount, TransactionType transactionType) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionType = transactionType;
       // this.balance = balance;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public int getContribution() {
        switch (transactionType) {
            case WITHDRAW:
                return -amount;
            case DEPOSIT:
                return amount;
        }
        throw new IllegalArgumentException("Invalid transaction of type: " + transactionType);
    }

//    public int getBalance() {
//        return balance;
//    }
//
//    public void setBalance(int balance) {
//        this.balance = balance;
//    }

    public void printTransaction(Console c, int balance) {
        String line;

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String output = formatter.format(this.transactionDate);
        line = output + " | ";

        if (this.transactionType == TransactionType.WITHDRAW) {
            line = line + "-";
        }

        line = line + this.amount + ".00 | " + balance + ".00";
        c.printLine(line);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                '}';
    }
}
