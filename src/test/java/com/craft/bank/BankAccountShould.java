package com.craft.bank;

import com.craft.domain.Transaction;
import com.craft.domain.TransactionBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.craft.domain.TransactionBuilder.*;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author flo
 * @since 03/07/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class BankAccountShould {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private StatementPrinter statementPrinter;
    @InjectMocks
    private BankAccount bankAccount;

    @Test
    public void givenABankAccountWhenDoingADepositThenTransactionIsCreated() {

        bankAccount.deposit(100);

        verify(transactionRepository).create(100);
    }

    @Test
    public void givenABankAccountWhenDoingAWithdrawThenTransactionIsCreated() {

        bankAccount.withdraw(100);

        verify(transactionRepository).create(-100);
    }

    @Test
    public void givenABankAccountWhenPrintingAStatementThenAllTransactionsArePartOfTheStatement() {
        List<Transaction> transactions = singletonList(aTransaction().defaults().build());
        doReturn(transactions).when(transactionRepository).all();

        bankAccount.printStatement();

        verify(transactionRepository).all();
        verify(statementPrinter).print(transactions);
    }


}
