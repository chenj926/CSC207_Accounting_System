package entity;

import java.time.LocalDate;

public class PeriodicInflow extends PeriodicTransaction {
    public PeriodicInflow(String identification, double amount, LocalDate date, String description, int recurrencePeriodInDays) {
        super(identification, amount, date, description, recurrencePeriodInDays);
        if(!this.isInflow()) System.out.println("Periodic inflow error, should be outflow!");
    }
}

/*
public class PeriodicInflow extends PeriodicTransaction {
    private String incomeSource;

    public PeriodicInflow(float transactionAmount, String transactionDate, String transactionDescription, String recurrence, String incomeSource) {
        super(transactionAmount, transactionDate, transactionDescription, recurrence);
        this.incomeSource = incomeSource;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    @Override
    public void recordTransaction() {
        System.out.println("Recording Income: ");
        displayTransactionDetails();
        System.out.println("Source: " + incomeSource);
    }
}

 */
