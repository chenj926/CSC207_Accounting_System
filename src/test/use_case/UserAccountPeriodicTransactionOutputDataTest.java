package use_case;

import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserAccountPeriodicTransactionOutputDataTest {

    private PeriodicInflow periodicInflow;
    private PeriodicOutflow periodicOutflow;
    private float newBalance;

    @BeforeEach
    public void setUp() {
        periodicInflow = new PeriodicInflow("inflow1", 100.0f, LocalDate.of(2024, 1, 1), "Salary", LocalDate.of(2024, 12, 31), "MOUTH", "Auto");
        periodicOutflow = new PeriodicOutflow("outflow1", 50.0f, LocalDate.of(2024, 1, 1), "Rent", LocalDate.of(2024, 12, 31), "MOUTH", "Auto");
        newBalance = 1000.0f;
    }

    @Test
    public void testPeriodicTransactionOutputDataInflow() {
        UserAccountPeriodicTransactionOutputData data = new UserAccountPeriodicTransactionOutputData(periodicInflow);
        assertNotNull(data);
        assertEquals(100.0f, data.getTransactionAmount());
        assertEquals(LocalDate.of(2024, 1, 1), data.getTransactionDate());
        assertEquals("Salary", data.getTransactionDescription());
        assertEquals(LocalDate.of(2024, 12, 31), data.getTransactionEndDate());
        assertEquals("MOUTH", data.getTransactionPeriod());
    }

    @Test
    public void testPeriodicTransactionOutputDataOutflow() {
        UserAccountPeriodicTransactionOutputData data = new UserAccountPeriodicTransactionOutputData(periodicOutflow);
        assertNotNull(data);
        assertEquals(50.0f, data.getTransactionAmount());
        assertEquals(LocalDate.of(2024, 1, 1), data.getTransactionStartDate());
        assertEquals("Rent", data.getTransactionDescription());
        assertEquals(LocalDate.of(2024, 12, 31), data.getTransactionEndDate());
        assertEquals("MOUTH", data.getTransactionPeriod());
    }

    @Test
    public void testSetters() {
        UserAccountPeriodicTransactionOutputData data = new UserAccountPeriodicTransactionOutputData(periodicInflow);

        data.setTransactionAmount(200.0f);
        assertEquals(200.0f, data.getTransactionAmount());

        String newDescription = "Bonus";
        data.setTransactionDescription(newDescription);
        assertEquals(newDescription, data.getTransactionDescription());

        LocalDate newDate = LocalDate.of(2024, 7, 17);
        data.setTransactionDate(newDate);
        assertEquals(newDate, data.getTransactionDate());
    }
}



