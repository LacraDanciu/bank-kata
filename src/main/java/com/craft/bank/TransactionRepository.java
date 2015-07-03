package com.craft.bank;

import com.craft.domain.Transaction;
import com.craft.infrastructure.ClockProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author flo
 * @since 02/07/15.
 */
public class TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();
    private ClockProvider clockProvider;

    public TransactionRepository(ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
    }

    public void create(int amount) {
        transactions.add(new Transaction(clockProvider.now(), amount));
    }

    public List<Transaction> all() {
       return Collections.unmodifiableList(transactions);
    }
}
