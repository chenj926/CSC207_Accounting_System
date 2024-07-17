package entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserAccountTest {

    private UserAccount account;

    @Before
    public void setUp() {
        account = new UserAccount("testUser", "testPass", "testID");
    }

    @Test
    public void testInitialValues() {
        assertEquals("Username doesn't match", "testUser", account.getUsername());
        assertEquals("Password doesn't match", "testPass", account.getPassword());
        assertEquals("Identification doesn't match", "testID", account.getIdentification());
        assertEquals("Initial total income should be 0.0", 0.0f, account.getTotalIncome(), 0.0f);
        assertEquals("Initial total outflow should be 0.0", 0.0f, account.getTotalOutflow(), 0.0f);
        assertEquals("Initial total balance should be 0.0", 0.0f, account.getTotalBalance(), 0.0f);
        assertTrue("Transactions should initially be empty", account.getTransactions().isEmpty());
    }

    @Test
    public void testAddTransaction() {
        UserAccountTestHelper incomeTransaction = new UserAccountTestHelper(100.0f);
        UserAccountTestHelper outflowTransaction = new UserAccountTestHelper(-50.0f);

        account.addTransaction(incomeTransaction);
        account.addTransaction(outflowTransaction);

        assertEquals("Total income should be 100.0", 100.0f, account.getTotalIncome(), 0.0f);
        assertEquals("Total outflow should be -50.0", -50.0f, account.getTotalOutflow(), 0.0f);
        assertEquals("Total balance should be 50.0", 50.0f, account.getTotalBalance(), 0.0f);

        ArrayList<Transaction> transactions = account.getTransactions();
        assertEquals("There should be 2 transactions", 2, transactions.size());
    }

    @Test
    public void testSetters() {
        account.setUsername("newUser");
        account.setPassword("newPass");
        account.setIdentification("newID");
        account.setTotalIncome(200.0f);
        account.setTotalOutflow(-100.0f);
        account.setTotalBalance(100.0f);

        assertEquals("Username doesn't match after set", "newUser", account.getUsername());
        assertEquals("Password doesn't match after set", "newPass", account.getPassword());
        assertEquals("Identification doesn't match after set", "newID", account.getIdentification());
        assertEquals("Total income doesn't match after set", 200.0f, account.getTotalIncome(), 0.0f);
        assertEquals("Total outflow doesn't match after set", -100.0f, account.getTotalOutflow(), 0.0f);
        assertEquals("Total balance doesn't match after set", 100.0f, account.getTotalBalance(), 0.0f);
    }
}

class UserAccountTestHelper implements Transaction {
    private float amount;

    public UserAccountTestHelper(float amount) {
        this.amount = amount;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public String getIdentification() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public float getAmount() {
        return amount;
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
}

class TransactionComparator implements java.util.Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return Float.compare(t1.getAmount(), t2.getAmount());
    }
}

