package entity;

import java.time.LocalDate;

public class PeriodicOutflow extends PeriodicTransaction {
    public PeriodicOutflow(String identification, float amount, LocalDate startDate, String description,
                           int recurrencePeriodInDays, LocalDate endDate, String duration) {
        super(identification, amount, startDate, description, recurrencePeriodInDays, endDate, duration);
    }
}

/*
public class PeriodicOutflow extends PeriodicTransaction {
    private String expenseCategory;

    public PeriodicOutflow(float transactionAmount, String transactionDate, String transactionDescription, String recurrence, String expenseCategory) {
        super();
        this.expenseCategory = expenseCategory;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    @Override
    public void recordTransaction() {
        System.out.println("Recording Expense: ");
        displayTransactionDetails();
        System.out.println("Category: " + expenseCategory);
    }
}

 */