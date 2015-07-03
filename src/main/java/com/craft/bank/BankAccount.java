package com.craft.bank;

import com.craft.domain.Account;
import com.craft.domain.Transaction;

import java.util.List;

public class BankAccount implements Account {

    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    public BankAccount(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        transactionRepository.create(amount);
    }

    public void withdraw(int amount) {
        transactionRepository.create(-amount);

    }

    public void printStatement() {
        List<Transaction> transactions = transactionRepository.all();
        statementPrinter.print(transactions);
    }
}
