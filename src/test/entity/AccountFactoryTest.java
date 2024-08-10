package entity;

import entity.account.AccountFactory;
import entity.account.UserAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountFactoryTest {

    private AccountFactory accountFactory;

    @Before
    public void setUp() {
        accountFactory = new AccountFactory();
    }

    @Test
    public void testCreateUserAccount() {
        UserAccount userAccount = accountFactory.createUserAccount("testUser", "testPass", "testID");

        assertNotNull("UserAccount should not be null", userAccount);
        assertEquals("Username doesn't match", "testUser", userAccount.getUsername());
        assertEquals("Password doesn't match", "testPass", userAccount.getPassword());
        assertEquals("Identification doesn't match", "testID", userAccount.getIdentification());
        assertEquals("Initial total income should be 0.0", 0.0f, userAccount.getTotalIncome(), 0.0f);
        assertEquals("Initial total outflow should be 0.0", 0.0f, userAccount.getTotalOutflow(), 0.0f);
        assertEquals("Initial total balance should be 0.0", 0.0f, userAccount.getTotalBalance(), 0.0f);
        assertTrue("Transactions should initially be empty", userAccount.getTransactions().isEmpty());
    }

}
