package com.craft.bank;

import com.craft.domain.TransactionBuilder;
import com.craft.infrastructure.Console;
import com.craft.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.craft.domain.TransactionBuilder.aTransaction;
import static java.time.LocalDate.now;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.inOrder;

/**
 * @author flo
 * @since 02/07/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    public static final int SALARY = 1000;
    public static final int RENT = -100;
    public static final int PLAY = -500;
    @Mock
    private Console console;

    @InjectMocks
    private StatementPrinter statementPrinter;

    @Test
    public void printAStatementOfTransactionsInReverseCronologicalOrder() {

        List<Transaction> transactions = Arrays.asList(
                aTransaction().withDate(now().minusMonths(1)).withAmount(SALARY).build(),
                aTransaction().withDate(now()).withAmount(RENT).build(),
                aTransaction().withDate(now().plusMonths(1)).withAmount(PLAY).build()
        );
        statementPrinter.print(transactions);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine(StatementPrinter.HEADER);
        inOrder.verify(console).printLine(contains(String.valueOf(PLAY)));
        inOrder.verify(console).printLine(contains(String.valueOf(RENT)));
        inOrder.verify(console).printLine(contains(String.valueOf(SALARY)));
        inOrder.verifyNoMoreInteractions();
    }
}
