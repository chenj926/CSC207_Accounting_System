package data_access.authentication.user_account;

import entity.account.user_account.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryLoginoutDataAccessObjectTest {

    private InMemoryLoginoutDataAccessObject dataAccessObject;
    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        dataAccessObject = new InMemoryLoginoutDataAccessObject();
        userAccount = new UserAccount("username", "password", "userAccountId");
        dataAccessObject.save(userAccount);
    }

    @Test
    void testExistById() {
        assertTrue(dataAccessObject.existById("userAccountId"));
        assertFalse(dataAccessObject.existById("nonExistentId"));
    }

    @Test
    void testLogin() {
        assertTrue(dataAccessObject.login(userAccount));
        assertTrue(dataAccessObject.existById("userAccountId"));
    }

    @Test
    void testLogout() {
        dataAccessObject.login(userAccount);
        dataAccessObject.logout(userAccount);
        // Assuming the existence check reflects login status, which isn't typically the case,
        // so just ensuring it toggles to false after logout in the map
        assertFalse(dataAccessObject.login(userAccount));
    }

    @Test
    void testGetById() {
        UserAccount retrievedAccount = dataAccessObject.getById("userAccountId");
        assertNotNull(retrievedAccount);
        assertEquals("userAccountId", retrievedAccount.getIdentification());
    }

    @Test
    void testSharedAccountsManagement() {
        String sharedAccountId = "sharedAccount1";
        userAccount.addSharedAccount(sharedAccountId);
        assertTrue(userAccount.getSharedAccounts().contains(sharedAccountId));

        userAccount.removeSharedAccount(sharedAccountId);
        assertFalse(userAccount.getSharedAccounts().contains(sharedAccountId));
    }
}


