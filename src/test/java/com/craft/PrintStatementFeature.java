package com.craft;

import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class PrintStatementFeature {

    @Test
    public void printedStatementShouldContainAccountOperations() {
        Account account = new BankAccount();
        Console console = mock(Console.class);
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