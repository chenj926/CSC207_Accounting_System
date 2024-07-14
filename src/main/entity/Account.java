package entity;

import java.util.List;

public interface Account {
    // getters
    String getIdentification();
    String getUsername();
    String getPassword();

    double getTotalIncome();
    double getTotalOutflow();
    double getTotalBalance();

    List<Transaction> getTransactions();

    // setters
    void setIdentification(String identification);
    void setUsername(String username);
    void setPassword(String password);

    void setTotalIncome(double totalIncome);
    void setTotalOutflow(double totalOutflow);
    void setTotalBalance(double totalBalance);

    void setTransactions(List<Transaction> transactions);

    // methods
    void addTransaction(Transaction transaction);

}

