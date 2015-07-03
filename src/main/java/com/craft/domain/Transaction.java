package com.craft.domain;

import java.time.LocalDate;

/**
 * @author flo
 * @since 02/07/15.
 */
public class Transaction {
    private LocalDate date;
    private Integer amount;

    public Transaction(LocalDate date, Integer amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getAmount() {
        return amount;
    }
}
