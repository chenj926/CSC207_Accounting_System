package data_access.transaction;

import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import entity.transaction.periodic.PeriodicTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryOneTimeDataAccessObjectTest {

    private InMemoryOneTimeDataAccessObject dataAccessObject;
    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        dataAccessObject = new InMemoryOneTimeDataAccessObject();
        userAccount = new UserAccount("testUser", "password123", "user001");

        // Add a one-time transaction to the user account
        Transaction transaction = new OneTimeTransaction("T001", 100.0f, LocalDate.of(2023, 8, 12), "Groceries", "Food");
        userAccount.addTransaction(transaction);

        // Save the user account
        dataAccessObject.save(userAccount);
    }

    @Test
    void testExistById() {
        assertTrue(dataAccessObject.existById("user001"));
        assertTrue(dataAccessObject.existById("user002"));
    }

    @Test
    void testGetById() {
        UserAccount retrievedAccount = dataAccessObject.getById("user001");
        assertNotNull(retrievedAccount);
        assertEquals("testUser", retrievedAccount.getUsername());
    }

    @Test
    void testUpdate() {
        userAccount.addTransaction(new OneTimeTransaction("T002", 50.0f, LocalDate.of(2023, 8, 13), "Books", "Education"));
        dataAccessObject.update(userAccount);

        UserAccount updatedAccount = dataAccessObject.getById("user001");
        assertNotNull(updatedAccount);
        assertEquals(2, updatedAccount.getTransactions().size());
    }

    @Test
    void testDeleteById() {
        dataAccessObject.deleteById("user001");
        assertFalse(dataAccessObject.existById("user001"));
    }

    @Test
    void testSave() {
        UserAccount newUserAccount = new UserAccount("newUser", "password456", "user002");
        dataAccessObject.save(newUserAccount);

        assertTrue(dataAccessObject.existById("user002"));
        assertEquals("newUser", dataAccessObject.getById("user002").getUsername());
    }


    @Test
    void testSaveTransaction() {
        // Create a OneTimeTransaction object
        OneTimeTransaction oneTimeTransaction = new OneTimeTransaction(
                "T001", // Transaction ID
                100.0f, // Amount
                LocalDate.now(), // Date
                "Groceries", // Description
                "Food" // Category
        );

        // Create UserAccountOneTimeTransactionOutputData with the OneTimeTransaction
        UserAccountOneTimeTransactionOutputData oneTimeOutputData =
                new UserAccountOneTimeTransactionOutputData(oneTimeTransaction);

        // Save one-time transaction
        dataAccessObject.saveTransaction(oneTimeOutputData, null, false);
    }



    @Test
    void testReadTransactions() {
        List<Transaction> transactions = dataAccessObject.readTransactions("user001");

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals("T001", transactions.get(0).getIdentification());
        assertTrue(transactions.get(0) instanceof OneTimeTransaction);
    }
}

