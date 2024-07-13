package main.entity;

public class PeriodicOutflow extends PeriodicTransaction {
    private String expenseCategory;

    public PeriodicOutflow(float transactionAmount, String transactionDate, String transactionDescription, String recurrence, String expenseCategory) {
        super(transactionAmount, transactionDate, transactionDescription, recurrence);
        this.expenseCategory = expenseCategory;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    @Override
    public void RecordTransaction() {
        System.out.println("Recording Expense: ");
        displayTransactionDetails();
        System.out.println("Category: " + expenseCategory);
    }
}