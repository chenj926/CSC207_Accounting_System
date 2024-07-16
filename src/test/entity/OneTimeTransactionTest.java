package entity;

import java.time.LocalDate;

public class OneTimeTransactionTest {

    public static void main(String[] args) {
        OneTimeTransaction transaction = new OneTimeOutflow("testID", -100.0f, LocalDate.of(2024, 7, 16), "Test description", "Test category");

        assert "testID".equals(transaction.getIdentification()) : "Identification doesn't match";
        assert transaction.getAmount() == -100.0f : "Amount doesn't match";
        assert LocalDate.of(2024, 7, 16).equals(transaction.getDate()) : "Date doesn't match";
        assert "Test description".equals(transaction.getDescription()) : "Description doesn't match";
        assert "Test category".equals(transaction.getCategory()) : "Category doesn't match";
        assert !transaction.isInflow() : "Inflow should be false";

        transaction.setIdentification("newID");
        transaction.setAmount(200.0f);
        transaction.setDate(LocalDate.of(2024, 8, 16));
        transaction.setDescription("New description");

        assert "newID".equals(transaction.getIdentification()) : "Identification doesn't match after set";
        assert transaction.getAmount() == 200.0f : "Amount doesn't match after set";
        assert LocalDate.of(2024, 8, 16).equals(transaction.getDate()) : "Date doesn't match after set";
        assert "New description".equals(transaction.getDescription()) : "Description doesn't match after set";
        assert transaction.isInflow() : "Inflow should be true after setting positive amount";

        System.out.println("All tests for OneTimeTransaction passed.");
    }
}
