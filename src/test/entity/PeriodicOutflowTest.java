package entity;

import java.time.LocalDate;

public class PeriodicOutflowTest {

    public static void main(String[] args) {
        PeriodicOutflow outflow = new PeriodicOutflow("testID", -100.0f, LocalDate.of(2024, 7, 16), "Test description", LocalDate.of(2024, 12, 31), 30);

        assert "testID".equals(outflow.getIdentification()) : "Identification doesn't match";
        assert outflow.getAmount() == -100.0f : "Amount doesn't match";
        assert LocalDate.of(2024, 7, 16).equals(outflow.getStartDate()) : "Start date doesn't match";
        assert LocalDate.of(2024, 12, 31).equals(outflow.getEndDate()) : "End date doesn't match";
        assert outflow.getPeriod() == 30 : "Period doesn't match";
        assert "Test description".equals(outflow.getDescription()) : "Description doesn't match";
        assert !outflow.isInflow() : "Inflow should be false";

        outflow.setIdentification("newID");
        outflow.setAmount(-200.0f);
        outflow.setStartDateDate(LocalDate.of(2024, 8, 16));
        outflow.setEndDate(LocalDate.of(2024, 11, 30));
        outflow.setPeriod(15);
        outflow.setDescription("New description");

        assert "newID".equals(outflow.getIdentification()) : "Identification doesn't match after set";
        assert outflow.getAmount() == -200.0f : "Amount doesn't match after set";
        assert LocalDate.of(2024, 8, 16).equals(outflow.getStartDate()) : "Start date doesn't match after set";
        assert LocalDate.of(2024, 11, 30).equals(outflow.getEndDate()) : "End date doesn't match after set";
        assert outflow.getPeriod() == 15 : "Period doesn't match after set";
        assert "New description".equals(outflow.getDescription()) : "Description doesn't match after set";
        assert !outflow.isInflow() : "Inflow should be false after setting negative amount";

        System.out.println("All tests for PeriodicOutflow passed.");
    }
}

