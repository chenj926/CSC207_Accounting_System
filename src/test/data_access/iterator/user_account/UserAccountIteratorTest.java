package data_access.iterator.user_account;

import entity.account.user_account.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountIteratorTest {

    private Path testCsvPath;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary CSV file for testing
        testCsvPath = Paths.get("test_user_accounts.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(testCsvPath)) {
            writer.write("id,username,password,income,outflow,balance,lastLoginDate,sharedIds\n");  // Header
            writer.write("UA001,alice,password1,1000.0,500.0,500.0,2023-08-10,SA001;SA002\n");
            writer.write("UA002,bob,password2,2000.0,1000.0,1000.0,null,\n");
        }
    }

    @Test
    void testIteration() throws IOException {
        try (UserAccountIterator iterator = new UserAccountIterator(testCsvPath)) {
            assertTrue(iterator.hasNext());

            // Test the first user account
            UserAccount account1 = iterator.next();
            assertEquals("UA001", account1.getIdentification());
            assertEquals("alice", account1.getUsername());
            assertEquals("password1", account1.getPassword());
            assertEquals(1000.0f, account1.getTotalIncome());
            assertEquals(500.0f, account1.getTotalOutflow());
            assertEquals(500.0f, account1.getTotalBalance());
            assertNotNull(account1.getLastLoginDate(), "Last login date should not be null for account1");
            assertEquals("2023-08-10", account1.getLastLoginDate().toString());
            assertEquals(Set.of("SA001", "SA002"), account1.getSharedAccounts());

            assertTrue(iterator.hasNext());

            // Test the second user account
            UserAccount account2 = iterator.next();
            assertEquals("UA002", account2.getIdentification());
            assertEquals("bob", account2.getUsername());
            assertEquals("password2", account2.getPassword());
            assertEquals(2000.0f, account2.getTotalIncome());
            assertEquals(1000.0f, account2.getTotalOutflow());
            assertEquals(1000.0f, account2.getTotalBalance());
            if (account2.getLastLoginDate() == null) {
                assertNull(account2.getLastLoginDate(), "Last login date is null for account2");
            } else {
                assertEquals("Expected date string", account2.getLastLoginDate().toString());
            }
            assertTrue(account2.getSharedAccounts().isEmpty());

            assertFalse(iterator.hasNext());
        }
    }


    @Test
    void testEmptyFile() throws IOException {
        // Create an empty CSV file
        Path emptyCsvPath = Paths.get("empty_user_accounts.csv");
        Files.createFile(emptyCsvPath);

        try (UserAccountIterator iterator = new UserAccountIterator(emptyCsvPath)) {
            assertFalse(iterator.hasNext());
        }

        // Clean up the empty file
        Files.deleteIfExists(emptyCsvPath);
    }
}

