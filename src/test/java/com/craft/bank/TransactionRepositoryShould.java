package com.craft.bank;

import com.craft.domain.Transaction;
import com.craft.infrastructure.ClockProvider;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Objects;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

/**
 * @author flo
 * @since 03/07/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

    @Mock
    private ClockProvider clockProvider;

    @InjectMocks
    private TransactionRepository transactionRepository;

    @Test
    public void addATransactionWithTheGivenAmmount() {

        transactionRepository.create(100);

        verify(clockProvider).now();

        assertThat(transactionRepository.all(), hasItem(aTransaction(100)));
    }

    private TypeSafeMatcher<Transaction> aTransaction(int amount) {
        return new TypeSafeMatcher<Transaction>() {
            @Override
            protected boolean matchesSafely(Transaction transaction) {
                return Objects.equals(transaction.getAmount(), amount);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("a transaction of amount " + amount);
            }
        };
    }
}
