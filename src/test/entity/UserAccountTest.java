package entity;

import entity.account.user_account.UserAccount;
import org.junit.Before;
import org.junit.Test;

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



