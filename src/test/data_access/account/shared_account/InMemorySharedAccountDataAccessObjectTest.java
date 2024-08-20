package data_access.account.shared_account;

import entity.account.shared_account.SharedAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemorySharedAccountDataAccessObjectTest {

    private InMemorySharedAccountDataAccessObject dao;
    private SharedAccount testAccount;

    @BeforeEach
    void setUp() {
        dao = new InMemorySharedAccountDataAccessObject();
        testAccount = new SharedAccount("sharedAccount1", Set.of("user1", "user2"), "password123");
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
        SharedAccount retrievedAccount = dao.getById(testAccount.getIdentification());

        // Verify the retrieved account is the same as the saved one
        assertNotNull(retrievedAccount);
        assertEquals(testAccount.getIdentification(), retrievedAccount.getIdentification());
        assertEquals(testAccount.getPassword(), retrievedAccount.getPassword());
        assertEquals(testAccount.getSharedUserIdentifications(), retrievedAccount.getSharedUserIdentifications());
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
        SharedAccount updatedAccount = new SharedAccount("sharedAccount1", Set.of("user1", "user2"), "newPassword123");
        dao.update(updatedAccount);

        // Retrieve the updated account
        SharedAccount retrievedAccount = dao.getById(testAccount.getIdentification());

        // Verify the update was successful
        assertNotNull(retrievedAccount);
        assertEquals("newPassword123", retrievedAccount.getPassword());
    }

    @Test
    void testGetAllShareAcc() {
        // Save two accounts
        SharedAccount account1 = new SharedAccount("sharedAccount1", Set.of("user1", "user2"), "password123");
        SharedAccount account2 = new SharedAccount("sharedAccount2", Set.of("user3", "user4"), "password456");

        dao.save(account1);
        dao.save(account2);

        // Retrieve all accounts
        Map<String, SharedAccount> allAccounts = dao.getAllShareAcc();

        // Verify both accounts are retrieved
        assertEquals(2, allAccounts.size());
        assertTrue(allAccounts.containsKey(account1.getIdentification()));
        assertTrue(allAccounts.containsKey(account2.getIdentification()));
    }

    @Test
    void testUpdateNonExistentAccountThrowsException() {
        // Attempt to update an account that does not exist
        SharedAccount nonExistentAccount = new SharedAccount("nonExistentAccount", Set.of("user5"), "password789");

        assertThrows(IllegalArgumentException.class, () -> dao.update(nonExistentAccount));
    }
}

