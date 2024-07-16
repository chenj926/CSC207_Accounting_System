package entity;

import java.time.LocalDate;

public class OneTimeInflowTest {

    public static void main(String[] args) {

        OneTimeInflow inflow = new OneTimeInflow("testID", 100.0f, LocalDate.of(2024, 7, 16), "Test description", "Test category");

        assert "testID".equals(inflow.getIdentification()) : "Identification doesn't match";
        assert inflow.getAmount() == 100.0f : "Amount doesn't match";
        assert LocalDate.of(2024, 7, 16).equals(inflow.getDate()) : "Date doesn't match";
        assert "Test description".equals(inflow.getDescription()) : "Description doesn't match";
        assert "Test category".equals(inflow.getCategory()) : "Category doesn't match";
        assert inflow.isInflow() : "Inflow should be true";

        inflow.setIdentification("newID");
        inflow.setAmount(200.0f);
        inflow.setDate(LocalDate.of(2024, 8, 16));
        inflow.setDescription("New description");

        assert "newID".equals(inflow.getIdentification()) : "Identification doesn't match after set";
        assert inflow.getAmount() == 200.0f : "Amount doesn't match after set";
        assert LocalDate.of(2024, 8, 16).equals(inflow.getDate()) : "Date doesn't match after set";
        assert "New description".equals(inflow.getDescription()) : "Description doesn't match after set";
        assert inflow.isInflow() : "Inflow should be true after setting positive amount";

        // If no assertion fails
        System.out.println("All tests for OneTimeInflow passed.");
    }
}
