package com.craft.bank;

import com.craft.domain.Transaction;
import com.craft.infrastructure.Console;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author flo
 * @since 02/07/15.
 */
public class StatementPrinter {
    protected static final String HEADER = "DATE | AMOUNT | BALANCE";

    private Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        AtomicInteger sum = new AtomicInteger(0);
        List<BalancedTransaction> amounts = transactions.stream()
                .map(i -> new BalancedTransaction(i.getDate(), i.getAmount(), sum.addAndGet(i.getAmount())))
                .collect(Collectors.toList());
        console.printLine(HEADER);
        amounts.stream()
                .sorted((i,j) -> j.getDate().compareTo(i.getDate()))
                .map(i -> MessageFormat.format("{0} | {1,number,#.00} | {2,number,#.00}",
                        i.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        i.getAmount(), i.getBalance()))
                .forEach(console::printLine);
    }

    private class BalancedTransaction extends Transaction {
        private Integer balance;

        public BalancedTransaction(LocalDate date, Integer amount, Integer balance) {
            super(date, amount);
            this.balance = balance;
        }

        public Integer getBalance() {
            return balance;
        }
    }
}
