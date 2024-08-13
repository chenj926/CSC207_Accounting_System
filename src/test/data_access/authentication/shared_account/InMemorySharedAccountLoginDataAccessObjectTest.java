package data_access.authentication.shared_account;

import entity.account.shared_account.SharedAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemorySharedAccountLoginDataAccessObjectTest {

    private InMemorySharedAccountLoginDataAccessObject dataAccessObject;
    private SharedAccount sharedAccount;

    @BeforeEach
    void setUp() {
        dataAccessObject = new InMemorySharedAccountLoginDataAccessObject();
        Set<String> userIds = new HashSet<>();
        userIds.add("user1");
        sharedAccount = new SharedAccount("sharedAccountId", userIds, "password");
        dataAccessObject.save(sharedAccount);
    }

    @Test
    void testExistById() {
        assertTrue(dataAccessObject.existById("sharedAccountId"));
        assertFalse(dataAccessObject.existById("nonExistentId"));
    }

    @Test
    void testLogin() {
        assertFalse(dataAccessObject.login(new SharedAccount("nonExistentId", new HashSet<>(), "password")));
        assertTrue(dataAccessObject.login(sharedAccount));
    }

    @Test
    void testGetById() {
        SharedAccount retrievedAccount = dataAccessObject.getById("sharedAccountId");
        assertTrue(retrievedAccount != null && retrievedAccount.getIdentification().equals("sharedAccountId"));
    }
}


