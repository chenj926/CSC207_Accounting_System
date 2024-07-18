package data_access;

import entity.SharedAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class InMemoryShareAccountDataAccessObjectTest {
    private InMemoryShareAccountDataAccessObject dao;
    private SharedAccount sharedAccount;

    @BeforeEach
    public void setUp() {
        dao = new InMemoryShareAccountDataAccessObject();
        sharedAccount = new SharedAccount("shared123");
        sharedAccount.addUserIdentification("user1");
    }

    @Test
    public void testExistById() {
        assertFalse(dao.existById("shared123"));
        dao.save(sharedAccount);
        assertTrue(dao.existById("shared123"));
    }

    @Test
    public void testSave() {
        dao.save(sharedAccount);
        assertTrue(dao.existById("shared123"));
    }

    @Test
    public void testUpdate() {
        dao.save(sharedAccount);
        assertTrue(dao.existById("shared123"));

        sharedAccount.addUserIdentification("user2");
        dao.save(sharedAccount);

        SharedAccount updatedAccount = dao.getById("shared123");
        assertEquals(2, updatedAccount.getSharedUserIdentifications().size());
        assertTrue(updatedAccount.getSharedUserIdentifications().contains("user2"));
    }

    @Test
    public void testDeleteById() {
        dao.save(sharedAccount);
        assertTrue(dao.existById("shared123"));

        dao.deleteById("shared123");
        assertFalse(dao.existById("shared123"));
    }

    @Test
    public void testGetById() {
        dao.save(sharedAccount);
        SharedAccount retrievedAccount = dao.getById("shared123");
        assertNotNull(retrievedAccount);
        assertEquals("shared123", retrievedAccount.getIdentification());
    }

    @Test
    public void testGetAllShareAcc() {
        dao.save(sharedAccount);
        Map<String, SharedAccount> allAccounts = dao.getAllShareAcc();
        assertEquals(1, allAccounts.size());
        assertTrue(allAccounts.containsKey("shared123"));
    }
}


