package data_access;

import entity.*;
import org.junit.jupiter.api.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CSVSharedAccountDataAccessObjectTest {

    private static final String SHARED_ACCOUNT_CSV_FILE_PATH = "data/sharedAccounts.csv";
    private static final String SHARED_ACCOUNT_USERS_CSV_FILE_PATH = "data/sharedAccountUsers.csv";
    private static final String SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH = "data/sharedAccountTransactions.csv";

    private CSVSharedAccountDataAccessObject dao;
    private SharedAccount testAccount;

    @BeforeEach
    public void setUp() throws Exception {
        dao = new CSVSharedAccountDataAccessObject();
        testAccount = new SharedAccount("testAccount123");
        testAccount.setUsername("testUser");
        testAccount.setPassword("testPassword");
        testAccount.setTotalIncome(1000.0f);
        testAccount.setTotalOutflow(500.0f);
        testAccount.setTotalBalance(500.0f);
        testAccount.addUserIdentification("user1");
        testAccount.addUserIdentification("user2");

        // Clean up files before each test
        Files.deleteIfExists(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH));
        Files.deleteIfExists(Paths.get(SHARED_ACCOUNT_USERS_CSV_FILE_PATH));
        Files.deleteIfExists(Paths.get(SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH));
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Clean up the files after each test
        Files.deleteIfExists(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH));
        Files.deleteIfExists(Paths.get(SHARED_ACCOUNT_USERS_CSV_FILE_PATH));
        Files.deleteIfExists(Paths.get(SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH));
    }

    @Test
    public void testSave() {
        dao.save(testAccount);
        SharedAccount retrievedAccount = dao.getById(testAccount.getIdentification());
        assertNotNull(retrievedAccount);
        assertEquals(testAccount.getUsername(), retrievedAccount.getUsername());
        assertEquals(testAccount.getPassword(), retrievedAccount.getPassword());
        assertEquals(testAccount.getTotalIncome(), retrievedAccount.getTotalIncome());
        assertEquals(testAccount.getTotalOutflow(), retrievedAccount.getTotalOutflow());
        assertEquals(testAccount.getTotalBalance(), retrievedAccount.getTotalBalance());
        assertEquals(testAccount.getSharedUserIdentifications(), retrievedAccount.getSharedUserIdentifications());
    }

    @Test
    public void testUpdate() {
        dao.save(testAccount);
        testAccount.setUsername("updatedUser");
        testAccount.setPassword("updatedPassword");
        testAccount.setTotalIncome(2000.0f);
        testAccount.setTotalOutflow(1000.0f);
        testAccount.setTotalBalance(1000.0f);
        dao.update(testAccount);

        SharedAccount retrievedAccount = dao.getById(testAccount.getIdentification());
        assertNotNull(retrievedAccount);
        assertEquals("updatedUser", retrievedAccount.getUsername());
        assertEquals("updatedPassword", retrievedAccount.getPassword());
        assertEquals(2000.0f, retrievedAccount.getTotalIncome());
        assertEquals(1000.0f, retrievedAccount.getTotalOutflow());
        assertEquals(1000.0f, retrievedAccount.getTotalBalance());
    }

    @Test
    public void testDeleteById() {
        dao.save(testAccount);
        dao.deleteById(testAccount.getIdentification());
        SharedAccount retrievedAccount = dao.getById(testAccount.getIdentification());
        assertNull(retrievedAccount);
    }

    @Test
    public void testGetById() {
        dao.save(testAccount);
        SharedAccount retrievedAccount = dao.getById(testAccount.getIdentification());
        assertNotNull(retrievedAccount);
        assertEquals(testAccount.getUsername(), retrievedAccount.getUsername());
        assertEquals(testAccount.getPassword(), retrievedAccount.getPassword());
    }

    @Test
    public void testExistById() {
        dao.save(testAccount);
        assertTrue(dao.existById(testAccount.getIdentification()));
        dao.deleteById(testAccount.getIdentification());
        assertFalse(dao.existById(testAccount.getIdentification()));
    }
}

