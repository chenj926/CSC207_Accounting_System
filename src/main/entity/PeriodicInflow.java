package entity;

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
