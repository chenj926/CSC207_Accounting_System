package main.interface_adaptors;

import main.interface_adaptors.TransactionViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PeriodicTransactionViewModel extends TransactionViewModel {
    public static final String TITLE_LABEL = "Periodic Transaction";
    public static final String TRANSACTION_AMOUNT_LABEL = "Transaction Amount";
    public static final String TRANSACTION_DATE_LABEL = "Transaction Date";
    public static final String TRANSACTION_DESCRIPTION_LABEL = "Transaction Description";
    public static final String RECURRENCE_LABEL = "Recurrence";

    private float transactionAmount;
    private String transactionDate;
    private String transactionDescription;
    private String recurrence;


    public PeriodicTransactionViewModel() {
        super("Periodic Transaction");
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        float oldTransactionAmount = this.transactionAmount;
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        String oldTransactionDate = this.transactionDate;
        this.transactionDate = transactionDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        String oldTransactionDescription = this.transactionDescription;
        this.transactionDescription = transactionDescription;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        String oldRecurrence = this.recurrence;
        this.recurrence = recurrence;
    }
}
