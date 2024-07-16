package entity;

import java.time.LocalDate;

public class PeriodicInflowTest {

    public static void main(String[] args) {
        PeriodicInflow inflow = new PeriodicInflow("testID", 100.0f, LocalDate.of(2024, 7, 16), "Test description", LocalDate.of(2024, 12, 31), 30);

        assert "testID".equals(inflow.getIdentification()) : "Identification doesn't match";
        assert inflow.getAmount() == 100.0f : "Amount doesn't match";
        assert LocalDate.of(2024, 7, 16).equals(inflow.getStartDate()) : "Start date doesn't match";
        assert LocalDate.of(2024, 12, 31).equals(inflow.getEndDate()) : "End date doesn't match";
        assert inflow.getPeriod() == 30 : "Period doesn't match";
        assert "Test description".equals(inflow.getDescription()) : "Description doesn't match";
        assert inflow.isInflow() : "Inflow should be true";

        inflow.setIdentification("newID");
        inflow.setAmount(200.0f);
        inflow.setStartDateDate(LocalDate.of(2024, 8, 16));
        inflow.setEndDate(LocalDate.of(2024, 11, 30));
        inflow.setPeriod(15);
        inflow.setDescription("New description");

        assert "newID".equals(inflow.getIdentification()) : "Identification doesn't match after set";
        assert inflow.getAmount() == 200.0f : "Amount doesn't match after set";
        assert LocalDate.of(2024, 8, 16).equals(inflow.getStartDate()) : "Start date doesn't match after set";
        assert LocalDate.of(2024, 11, 30).equals(inflow.getEndDate()) : "End date doesn't match after set";
        assert inflow.getPeriod() == 15 : "Period doesn't match after set";
        assert "New description".equals(inflow.getDescription()) : "Description doesn't match after set";
        assert inflow.isInflow() : "Inflow should be true after setting positive amount";

        System.out.println("All tests for PeriodicInflow passed.");
    }
}
