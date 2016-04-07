package com.craft;

import com.craft.bank.BankAccount;
import com.craft.bank.StatementPrinter;
import com.craft.bank.TransactionRepository;
import com.craft.domain.Account;
import com.craft.infrastructure.ClockProvider;
import com.craft.infrastructure.Console;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.time.Clock;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class PrintStatementFeature {

    private Account account;
    private Console console;

    @Before
    public void setUp() {
          console = mock(Console.class);
        ClockProvider clockProvider = mock(ClockProvider.class);
        when(clockProvider.now())
                .thenReturn(LocalDate.of(2016, 4, 1))
                .thenReturn(LocalDate.of(2016, 4, 2))
                .thenReturn(LocalDate.of(2016, 4, 10));
        account = new BankAccount(new TransactionRepository(clockProvider), new StatementPrinter(console));
    }

    @Test
    public void print_statement_containing_transactions_in_reverse_chronological_order() {
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2016 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2016 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2016 | 1000.00 | 1000.00");
        inOrder.verifyNoMoreInteractions();
    }
}