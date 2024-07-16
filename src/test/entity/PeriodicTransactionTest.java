package entity;

import java.time.LocalDate;

public class PeriodicTransactionTest {

    public static void main(String[] args) {
        PeriodicTransaction transaction = new PeriodicInflow("testID", 100.0f, LocalDate.of(2024, 7, 16), "Test description", LocalDate.of(2024, 12, 31), 30);

        assert "testID".equals(transaction.getIdentification()) : "Identification doesn't match";
        assert transaction.getAmount() == 100.0f : "Amount doesn't match";
        assert LocalDate.of(2024, 7, 16).equals(transaction.getStartDate()) : "Start date doesn't match";
        assert LocalDate.of(2024, 12, 31).equals(transaction.getEndDate()) : "End date doesn't match";
        assert transaction.getPeriod() == 30 : "Period doesn't match";
        assert "Test description".equals(transaction.getDescription()) : "Description doesn't match";
        assert transaction.isInflow() : "Inflow should be true";

        transaction.setIdentification("newID");
        transaction.setAmount(-200.0f);
        transaction.setStartDateDate(LocalDate.of(2024, 8, 16));
        transaction.setEndDate(LocalDate.of(2024, 11, 30));
        transaction.setPeriod(15);
        transaction.setDescription("New description");

        assert "newID".equals(transaction.getIdentification()) : "Identification doesn't match after set";
        assert transaction.getAmount() == -200.0f : "Amount doesn't match after set";
        assert LocalDate.of(2024, 8, 16).equals(transaction.getStartDate()) : "Start date doesn't match after set";
        assert LocalDate.of(2024, 11, 30).equals(transaction.getEndDate()) : "End date doesn't match after set";
        assert transaction.getPeriod() == 15 : "Period doesn't match after set";
        assert "New description".equals(transaction.getDescription()) : "Description doesn't match after set";
        assert !transaction.isInflow() : "Inflow should be false after setting negative amount";

        System.out.println("All tests for PeriodicTransaction passed.");
    }
}
