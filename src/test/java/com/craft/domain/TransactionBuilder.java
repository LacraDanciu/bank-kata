package com.craft.domain;

import java.time.LocalDate;

/**
 * @author flo
 * @since 03/07/15.
 */
public class TransactionBuilder {

    private LocalDate date;
    private Integer amount;

    public static TransactionBuilder aTransaction() {
        return new TransactionBuilder();
    }

    public Transaction build() {
        return new Transaction(date, amount);
    }

    public TransactionBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public TransactionBuilder withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder defaults() {
        this.date = LocalDate.now();
        this.amount = 100;
        return this;
    }



}
