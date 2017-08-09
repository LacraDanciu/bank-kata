package com.craft;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BankAccount implements Account {

    private int balance;
    private List<Transaction> transactions = new ArrayList<>();
    private Console console;
    private DateProvider dateProvider;


    public BankAccount(Console console, DateProvider dateProvider) {
        this.console = console;
        this.dateProvider = dateProvider;
    }

    public void deposit(int amount) {
        buildTransaction(amount, TransactionType.DEPOSIT);
    }

    public void withdraw(int amount) {
        buildTransaction(amount, TransactionType.WITHDRAW);
    }

    private void buildTransaction(int amount, TransactionType deposit) {
        Transaction newTransaction = new Transaction(dateProvider.now(), amount, deposit);
        this.balance = this.balance + newTransaction.getContribution();
        transactions.add(newTransaction);
    }

    public void printStatement() {
        console.printLine("DATE | AMOUNT | BALANCE");
        int balance = this.balance;

        ListIterator li = this.transactions.listIterator(this.transactions.size());
        while (li.hasPrevious()) {
            Transaction tr = (Transaction) li.previous();
            tr.printTransaction(console, balance);
            balance -= tr.getContribution();
        }
    }
}
