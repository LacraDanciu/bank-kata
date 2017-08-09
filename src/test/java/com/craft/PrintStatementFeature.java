package com.craft;

import org.junit.Test;
import org.mockito.InOrder;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrintStatementFeature {

    @Test
    public void printedStatementShouldContainAccountOperations() {
        Console console = mock(Console.class);
        DateProvider dateProvider = mock(DateProvider.class);
        when(dateProvider.now())
                .thenReturn(buildDate(2016, 04, 01))
                .thenReturn(buildDate(2016, 04, 02))
                .thenReturn(buildDate(2016, 04, 10));
        Account account = new BankAccount(console, dateProvider);
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

    private Date buildDate(int year, int month, int dayOfMonth) {
        return Date.from(LocalDate.of(year, month, dayOfMonth).atStartOfDay().toInstant(ZoneOffset.UTC));
    }
}