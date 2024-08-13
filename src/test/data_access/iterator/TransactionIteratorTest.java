package data_access.iterator;

import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import entity.transaction.periodic.PeriodicTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionIteratorTest {

    private Path testCsvPath;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary CSV file for testing
        testCsvPath = Paths.get("test_transactions.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(testCsvPath)) {
            writer.write("id,amount,date,description,category,startDate,period,endDate\n");  // Header
            writer.write("T001,100.0,2023-08-10,Groceries,Food\n"); // One-time transaction
            writer.write("T002,50.0,2023-08-11,Subscription,Entertainment,2023-08-01,MONTHLY,2023-12-01\n"); // Periodic transaction
        }
    }

    @Test
    void testIteration() throws IOException {
        try (TransactionIterator iterator = new TransactionIterator(testCsvPath)) {
            assertTrue(iterator.hasNext());

            // Test the first one-time transaction
            Transaction transaction1 = iterator.next();
            assertTrue(transaction1 instanceof OneTimeTransaction);
            assertEquals("T001", transaction1.getIdentification());
            assertEquals(100.0f, transaction1.getAmount());
            assertEquals(LocalDate.of(2023, 8, 10), transaction1.getDate());
            assertEquals("Groceries", transaction1.getDescription());
            assertEquals("Food", transaction1.getTransactionCategory());

            assertTrue(iterator.hasNext());

            // Test the second periodic transaction
            Transaction transaction2 = iterator.next();
            assertTrue(transaction2 instanceof PeriodicTransaction);
            assertEquals("T002", transaction2.getIdentification());
            assertEquals(50.0f, transaction2.getAmount());
            assertEquals(LocalDate.of(2023, 8, 11), transaction2.getDate());
            assertEquals("Subscription", transaction2.getDescription());
            assertEquals("Entertainment", transaction2.getTransactionCategory());
            assertEquals(LocalDate.of(2023, 8, 1), ((PeriodicTransaction) transaction2).getStartDate());
            assertEquals("MONTHLY", ((PeriodicTransaction) transaction2).getPeriod());
            assertEquals(LocalDate.of(2023, 12, 1), ((PeriodicTransaction) transaction2).getEndDate());

            assertFalse(iterator.hasNext());
        }
    }

    @Test
    void testEmptyFile() throws IOException {
        // Create an empty CSV file
        Path emptyCsvPath = Paths.get("empty_transactions.csv");
        Files.createFile(emptyCsvPath);

        try (TransactionIterator iterator = new TransactionIterator(emptyCsvPath)) {
            assertFalse(iterator.hasNext());
        }

        // Clean up the empty file
        Files.deleteIfExists(emptyCsvPath);
    }


    // Clean up the test CSV file after each test
    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(testCsvPath);
    }
}

