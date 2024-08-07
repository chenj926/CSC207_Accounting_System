package data_access.transaction;

import entity.transaction.Transaction;

import java.util.List;

public interface TransactionDataAccessInterface<O, P> {
    void saveTransaction(O oneTimeOutputData, P periodicOutputData, boolean isPeriodic);
    List<Transaction> readTransactions(String userId);
}
