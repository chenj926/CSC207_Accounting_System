package data_access;

import data_access.account.InMemoryShareAccountDataAccessObject;
import entity.account.SharedAccount;
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
        SharedAccount fetchedAccount = dao.getById("shared123");
        assertNotNull(fetchedAccount);
        assertEquals("shared123", fetchedAccount.getIdentification());
        assertTrue(fetchedAccount.getSharedUserIdentifications().contains("user1"));
    }

    @Test
    public void testGetAllShareAcc() {
        dao.save(sharedAccount);
        SharedAccount anotherSharedAccount = new SharedAccount("shared456");
        anotherSharedAccount.addUserIdentification("user2");
        dao.save(anotherSharedAccount);

        Map<String, SharedAccount> allAccounts = dao.getAllShareAcc();
        assertEquals(2, allAccounts.size());
        assertTrue(allAccounts.containsKey("shared123"));
        assertTrue(allAccounts.containsKey("shared456"));
    }
}



