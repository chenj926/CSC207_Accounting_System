package data_access.transaction;

import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPeriodicDataAccessObjectTest {

    private InMemoryPeriodicDataAccessObject dataAccessObject;
    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        dataAccessObject = new InMemoryPeriodicDataAccessObject();
        userAccount = new UserAccount("testUser", "password123", "user001");

        // Add a periodic transaction to the user account
        Transaction transaction = new PeriodicTransaction("T001", 200.0f, LocalDate.of(2023, 8, 12),
                "Rent", LocalDate.of(2024, 8, 12), "Monthly", "Housing");
        userAccount.addTransaction(transaction);

        // Save the user account
        dataAccessObject.save(userAccount);
    }

    @Test
    void testExistById() {
        assertTrue(dataAccessObject.existById("user001"));
        assertFalse(dataAccessObject.existById("user002"));
    }

    @Test
    void testGetById() {
        UserAccount retrievedAccount = dataAccessObject.getById("user001");
        assertNull(retrievedAccount); // As the method is not implemented yet
    }

    @Test
    void testUpdate() {
        userAccount.addTransaction(new PeriodicTransaction("T002", 150.0f, LocalDate.of(2023, 8, 13),
                "Subscription", LocalDate.of(2024, 8, 13), "Monthly", "Entertainment"));
        dataAccessObject.update(userAccount);
    }

    @Test
    void testDeleteById() {
        dataAccessObject.deleteById("user001");
    }

    @Test
    void testSave() {
        UserAccount newUserAccount = new UserAccount("newUser", "password456", "user002");
        dataAccessObject.save(newUserAccount);
    }

    @Test
    void testSaveTransaction() {
        // Create a PeriodicTransaction object
        PeriodicTransaction periodicTransaction = new PeriodicTransaction(
                "T002", // Transaction ID
                200.0f, // Amount
                LocalDate.now(), // Start Date
                "Rent", // Description
                LocalDate.now().plusMonths(1), // End Date
                "Monthly", // Period
                "Housing" // Category
        );

        // Create UserAccountPeriodicTransactionOutputData with the PeriodicTransaction
        UserAccountPeriodicTransactionOutputData periodicOutputData =
                new UserAccountPeriodicTransactionOutputData(periodicTransaction);

        // Save periodic transaction
        dataAccessObject.saveTransaction(null, periodicOutputData, true);

        // Check the console output manually to verify the save transaction messages
    }
}

