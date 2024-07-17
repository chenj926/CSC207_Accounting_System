package entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TransactionComparatorTest {

    private TransactionComparator comparator;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;

    @Before
    public void setUp() {
        comparator = new TransactionComparator();

        t1 = new TransactionComparatorTestHelper("1", 100.0, LocalDate.of(2023, 1, 1));
        t2 = new TransactionComparatorTestHelper("2", 100.0, LocalDate.of(2023, 1, 1));
        t3 = new TransactionComparatorTestHelper("3", 200.0, LocalDate.of(2024, 1, 1));
    }

    @Test
    public void testCompareByDate() {
        Transaction earlier = new TransactionComparatorTestHelper("4", 50.0, LocalDate.of(2022, 1, 1));
        assertTrue("Earlier transaction should come before later one", comparator.compare(earlier, t1) < 0);
        assertTrue("Later transaction should come after earlier one", comparator.compare(t1, earlier) > 0);
    }

    @Test
    public void testCompareByAmount() {
        assertTrue("Transaction with smaller amount should come before larger one", comparator.compare(t1, t3) < 0);
        assertTrue("Transaction with larger amount should come after smaller one", comparator.compare(t3, t1) > 0);
    }

    @Test
    public void testCompareEqualTransactions() {
        Transaction equal1 = new TransactionComparatorTestHelper("5", 100.0, LocalDate.of(2023, 1, 1));
        Transaction equal2 = new TransactionComparatorTestHelper("5", 100.0, LocalDate.of(2023, 1, 1));
        assertEquals("Equal transactions should compare as zero", 0, comparator.compare(equal1, equal2));
    }
}

class TransactionComparatorTestHelper implements Transaction {
    private String id;
    private double amount;
    private LocalDate date;

    public TransactionComparatorTestHelper(String id, double amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String getIdentification() {
        return id;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public float getAmount() {
        return (float) amount;
    }

    @Override
    public void setDate(LocalDate date) {

    }

    @Override
    public void setIdentification(String identification) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setAmount(float amount) {

    }

    @Override
    public LocalDate getDate() {
        return date;
    }
}

