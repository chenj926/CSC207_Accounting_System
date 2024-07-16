package entity;

import java.time.LocalDate;

public class OneTimeOutflowTest {

    public static void main(String[] args) {
        OneTimeOutflow outflow = new OneTimeOutflow("testID", -100.0f, LocalDate.of(2024, 7, 16), "Test description", "Test category");

        assert "testID".equals(outflow.getIdentification()) : "Identification doesn't match";
        assert outflow.getAmount() == -100.0f : "Amount doesn't match";
        assert LocalDate.of(2024, 7, 16).equals(outflow.getDate()) : "Date doesn't match";
        assert "Test description".equals(outflow.getDescription()) : "Description doesn't match";
        assert "Test category".equals(outflow.getCategory()) : "Category doesn't match";
        assert !outflow.isInflow() : "Inflow should be false";

        outflow.setIdentification("newID");
        outflow.setAmount(-200.0f);
        outflow.setDate(LocalDate.of(2024, 8, 16));
        outflow.setDescription("New description");

        assert "newID".equals(outflow.getIdentification()) : "Identification doesn't match after set";
        assert outflow.getAmount() == -200.0f : "Amount doesn't match after set";
        assert LocalDate.of(2024, 8, 16).equals(outflow.getDate()) : "Date doesn't match after set";
        assert "New description".equals(outflow.getDescription()) : "Description doesn't match after set";
        assert !outflow.isInflow() : "Inflow should be false after setting negative amount";

        System.out.println("All tests for OneTimeOutflow passed.");
    }
}
