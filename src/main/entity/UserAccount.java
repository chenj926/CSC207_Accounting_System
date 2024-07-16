package entity;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class UserAccount implements Account {
    private String username;
    private String password;
    private String identification;

    private List<Transaction> transactions;

    private float totalIncome;
    private float totalOutflow;
    private float totalBalance;

    // constructors
    public UserAccount(String username, String password, String identification) {
        this.username = username;
        this.password = password;
        this.identification = identification;

        this.transactions = new ArrayList<>();

        this.totalIncome = 0.0f;
        this.totalOutflow = 0.0f;
        this.totalBalance = 0.0f;
    }

    public UserAccount(String username, String password, String identification,
                       float totalIncome, float totalOutflow, float totalBalance) {
        this.username = username;
        this.password = password;
        this.identification = identification;

        this.transactions = new ArrayList<>();

        this.totalIncome = totalIncome;
        this.totalOutflow = totalOutflow;
        this.totalBalance = totalBalance;
    }

    // getters
    @Override
    public String getIdentification() {
        return identification;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public float getTotalIncome() {
        return totalIncome;
    }
    @Override
    public float getTotalOutflow() {
        return totalOutflow;
    }
    @Override
    public float getTotalBalance() {
        return totalBalance;
    }

    @Override
    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    // setters
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setTotalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
    }
    @Override
    public void setTotalOutflow(float totalOutflow) {
        this.totalOutflow = totalOutflow;
    }
    @Override
    public void setTotalBalance(float totalBalance) {
        this.totalBalance = totalBalance;
    }

    @Override
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // add transaction object
    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        updateTotals(transaction);
        transactions.sort(new TransactionComparator());
    }

    // update total income, total outflow, and total balance for each transaction
    private void updateTotals(Transaction transaction) {
        float amount = transaction.getAmount();
        if (amount > 0) {
            totalIncome += amount;
        } else {
            totalOutflow += amount;
        }
        totalBalance += amount;
    }
}