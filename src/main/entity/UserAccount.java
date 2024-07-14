package entity;

import java.util.List;
import java.util.ArrayList;


public class UserAccount {
    private String username;
    private String password;
    private String identification;
    private List<Transaction> transactions;

    public UserAccount(String username, String password, String identification) {
        this.username = username;
        this.password = password;
        this.identification = identification;
        this.transactions = new ArrayList<>();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentification() {
        return identification;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public void recordTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}


/*
public class UserAccount implements entity.Transaction {
    private String identification;

    private float totalCurrentBalance;
    private float totalIncome;
    private float totalOutflow;

    protected List<Transaction> transactions;

    // Constructors
    public UserAccount(String identification){
        this.totalCurrentBalance = 0.0f;
        this.totalIncome = 0.0f;
        this.totalOutflow = 0.0f;
        this.identification = identification;
    }
    public UserAccount(float balance, float income, float outflow, String identification) {
        this.totalCurrentBalance = balance;
        this.totalIncome = income;
        this.totalOutflow = outflow;
        this.identification = identification;
    }

    // Getters
    public float getTotalCurrentBalance() {
        return this.totalCurrentBalance;
    }
    public float getTotalIncome() {
        return this.totalIncome;
    }
    public float getTotalOutflow() {
        return this.totalOutflow;
    }
    public String getIdentification() {
        return this.identification;
    }

    // Setters
    public void setTotalCurrentBalance(float balance) {
        this.totalCurrentBalance = balance;
    }
    public void setTotalIncome(float income) {
        this.totalIncome = income;
    }
    public void setTotalOutflow(float outflow) {
        this.totalOutflow = outflow;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    // implement the interface's recordTransaction method
    @Override
    public void recordTransaction(String identification, float transactionAmount,
                                  LocalDate transactionDate, String transactionDescription,
                                  String recurrence, boolean periodic){

        boolean inflow = (transactionAmount > 0.0f);
        if (transactionAmount >= 0.0) {
            this.totalIncome += transactionAmount;
        }
        else {
            this.totalOutflow += Math.abs(transactionAmount);  // let the outflow to p+ first
        }
        this.totalCurrentBalance += transactionAmount;

        if(periodic){
            if(inflow){
                // periodic inflow

            }else{
                // periodic outflow

            }
        } else{
            if(inflow){
                // one time inflow

            }else{
                // one time outflow

            }
        }

    }
}
 */