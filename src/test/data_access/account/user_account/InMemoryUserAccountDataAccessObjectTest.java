package data_access.account.user_account;

import entity.account.user_account.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserAccountDataAccessObjectTest {

    private InMemoryUserAccountDataAccessObject dao;
    private UserAccount testAccount;

    @BeforeEach
    void setUp() {
        dao = new InMemoryUserAccountDataAccessObject();
        testAccount = new UserAccount("userAccount1", "password123","id1");
    }

    @Test
    void testSaveAndExistById() {
        // Before saving the account, it should not exist
        assertFalse(dao.existById(testAccount.getIdentification()));

        // Save the account
        dao.save(testAccount);

        // Now the account should exist
        assertTrue(dao.existById(testAccount.getIdentification()));
    }

    @Test
    void testGetById() {
        // Save the account
        dao.save(testAccount);

        // Retrieve the account by ID
        UserAccount retrievedAccount = dao.getById(testAccount.getIdentification());

        // Verify the retrieved account is the same as the saved one
        assertNotNull(retrievedAccount);
        assertEquals(testAccount.getIdentification(), retrievedAccount.getIdentification());
        assertEquals(testAccount.getPassword(), retrievedAccount.getPassword());
    }

    @Test
    void testDeleteById() {
        // Save the account
        dao.save(testAccount);

        // Delete the account by ID
        dao.deleteById(testAccount.getIdentification());

        // The account should no longer exist
        assertFalse(dao.existById(testAccount.getIdentification()));
        assertNull(dao.getById(testAccount.getIdentification()));
    }

    @Test
    void testUpdate() {
        // Save the account
        dao.save(testAccount);

        // Update the account (e.g., change the password)
        UserAccount updatedAccount = new UserAccount("userAccount1", "newPassword123","id1");
        dao.update(updatedAccount);

        // Retrieve the updated account
        UserAccount retrievedAccount = dao.getById(testAccount.getIdentification());

        // Verify the update was successful
        assertNotNull(retrievedAccount);
        assertEquals("newPassword123", retrievedAccount.getPassword());
    }

    @Test
    void testGetAllUserAcc() {
        // Save two accounts
        UserAccount account1 = new UserAccount("userAccount1", "password123", "id1");
        UserAccount account2 = new UserAccount("userAccount2", "password456","id2");

        dao.save(account1);
        dao.save(account2);

        // Retrieve all accounts
        Map<String, UserAccount> allAccounts = dao.getAllUserAcc();

        // Verify both accounts are retrieved
        assertEquals(2, allAccounts.size());
        assertTrue(allAccounts.containsKey(account1.getIdentification()));
        assertTrue(allAccounts.containsKey(account2.getIdentification()));
    }

    @Test
    void testUpdateNonExistentAccountThrowsException() {
        // Attempt to update an account that does not exist
        UserAccount nonExistentAccount = new UserAccount("nonExistentAccount", "password789","id3");

        assertThrows(IllegalArgumentException.class, () -> dao.update(nonExistentAccount));
    }
}


