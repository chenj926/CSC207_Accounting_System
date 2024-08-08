package data_access.transaction;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.one_time.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.UserAccountPeriodicTransactionOutputData;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryOneTimeDataAccessObject implements UserAccountDataAccessInterface {
    private static Map<String, UserAccount> users = new HashMap<>();
    private static Map<String, SharedAccountOneTimeTransactionOutputData> sharedAccountOneTimeTransactions = new HashMap<>();
    private static Map<String, SharedAccountPeriodicTransactionOutputData> sharedAccountPeriodicTransactions = new HashMap<>();

    public InMemoryOneTimeDataAccessObject() {
        // Initialize the user map if needed
    }

    @Override
    public boolean existById(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public UserAccount getById(String identification) {
        return users.get(identification);
    }

    @Override
    public void update(UserAccount userAccount) {
        users.put(userAccount.getIdentification(), userAccount);
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    @Override
    public void save(UserAccount userAccount) {
        users.put(userAccount.getIdentification(), userAccount);
    }

    @Override
    public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData,
                                UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {
        if (isPeriodic) {
            System.out.println("Saving periodic transaction data...");
        } else {
            System.out.println("Saving one-time transaction data...");
        }
    }

    @Override
    public List<Transaction> readTransactions(String userId) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(users.get(userId).getTransactions());
        transactions.removeIf(transaction -> transaction instanceof PeriodicTransaction);
        return transactions;
    }

    // Additional methods to retrieve shared transactions can be added as needed
    public SharedAccountOneTimeTransactionOutputData getSharedOneTimeTransaction(String transactionId) {
        return sharedAccountOneTimeTransactions.get(transactionId);
    }

    public SharedAccountPeriodicTransactionOutputData getSharedPeriodicTransaction(String transactionId) {
        return sharedAccountPeriodicTransactions.get(transactionId);
    }
}


//    @Override
//    public List<Transaction> readTransactions(String identification) {
//        List<Transaction> transactions= new ArrayList<>();
//        return transactions;
//    }


