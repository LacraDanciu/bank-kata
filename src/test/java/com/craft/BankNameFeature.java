package com.craft;

import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

/**
 * @author flo
 * @since 14/04/16.
 */
public class BankNameFeature {

    private static final String BANK_OF_TEST = "BankOfTest";

    @Test
    public void theBankShouldReportItsName() {
        Console aConsole = mock(Console.class);
        Bank theBank = new Bank(BANK_OF_TEST, aConsole);

        theBank.reportName();

        InOrder inOrder = inOrder(aConsole);
        inOrder.verify(aConsole).printLine(Bank.BANK_NAME_HEADER);
        inOrder.verify(aConsole).printLine(BANK_OF_TEST);
        inOrder.verifyNoMoreInteractions();
    }


}
