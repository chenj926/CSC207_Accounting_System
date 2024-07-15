package entity;

import java.util.List;

public interface Account {
    // getters
    String getIdentification();
    String getUsername();
    String getPassword();

    float getTotalIncome();
    float getTotalOutflow();
    float getTotalBalance();

    List<Transaction> getTransactions();

    // setters
    void setIdentification(String identification);
    void setUsername(String username);
    void setPassword(String password);

    void setTotalIncome(float totalIncome);
    void setTotalOutflow(float totalOutflow);
    void setTotalBalance(float totalBalance);

    void setTransactions(List<Transaction> transactions);

    // methods
    void addTransaction(Transaction transaction);

}

