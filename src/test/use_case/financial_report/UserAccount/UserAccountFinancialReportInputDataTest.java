package use_case.financial_report.UserAccount;

import org.junit.jupiter.api.Test;
import use_case.financial_report.user_account.UserAccountFinancialReportInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountFinancialReportInputDataTest {
    @Test
    public void testConstructorAndGettersAdnSetters() {
        String userId = "id0";
        UserAccountFinancialReportInputData inputData = new UserAccountFinancialReportInputData(userId);

        assertEquals(userId, inputData.getIdentification());
    }
}
