package entity;

import java.util.List;

public interface Account {
    String getIdentification();
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
    double getTotalIncome();
    double getTotalOutflow();
    double getTotalBalance();
}

