package data_access.iterator.shared_account;

import entity.account.shared_account.SharedAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountIteratorTest {

    private Path testCsvPath;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary CSV file for testing
        testCsvPath = Paths.get("test_shared_accounts.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(testCsvPath)) {
            writer.write("id,userIds,password,income,outflow,balance,lastLoginDate\n");  // Header
            writer.write("SA001,user1;user2;user3,password1,1000.0,500.0,500.0,2023-08-10\n");
            writer.write("SA002,user4;user5;user6,password2,2000.0,1000.0,1000.0,null\n");
        }
    }

    @Test
    void testIteration() throws IOException {
        try (SharedAccountIterator iterator = new SharedAccountIterator(testCsvPath)) {
            assertTrue(iterator.hasNext());

            // Test the first shared account
            SharedAccount account1 = iterator.next();
            assertEquals("SA001", account1.getIdentification());
            assertEquals(Set.of("user1", "user2", "user3"), account1.getSharedUserIdentifications());
            assertEquals("password1", account1.getPassword());
            assertEquals(1000.0f, account1.getTotalIncome());
            assertEquals(500.0f, account1.getTotalOutflow());
            assertEquals(500.0f, account1.getTotalBalance());
            assertEquals("2023-08-10", account1.getLastLoginDate().toString());

            assertTrue(iterator.hasNext());

            // Test the second shared account
            SharedAccount account2 = iterator.next();
            assertEquals("SA002", account2.getIdentification());
            assertEquals(Set.of("user4", "user5", "user6"), account2.getSharedUserIdentifications());
            assertEquals("password2", account2.getPassword());
            assertEquals(2000.0f, account2.getTotalIncome());
            assertEquals(1000.0f, account2.getTotalOutflow());
            assertEquals(1000.0f, account2.getTotalBalance());

            assertFalse(iterator.hasNext());
        }
    }

    @Test
    void testEmptyFile() throws IOException {
        // Create an empty CSV file
        Path emptyCsvPath = Paths.get("empty_shared_accounts.csv");
        Files.createFile(emptyCsvPath);

        try (SharedAccountIterator iterator = new SharedAccountIterator(emptyCsvPath)) {
            assertFalse(iterator.hasNext());
        }

        // Clean up the empty file
        Files.deleteIfExists(emptyCsvPath);
    }
}

