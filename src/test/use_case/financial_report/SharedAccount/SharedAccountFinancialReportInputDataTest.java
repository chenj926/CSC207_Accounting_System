package use_case.financial_report.SharedAccount;
import org.junit.jupiter.api.Test;
import use_case.financial_report.shared_account.SharedAccountFinancialReportInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SharedAccountFinancialReportInputDataTest {
    @Test
    public void testConstructorAndGettersAdnSetters() {
        String sharedId = "sd0";
        SharedAccountFinancialReportInputData inputData = new SharedAccountFinancialReportInputData(sharedId);

        assertEquals(sharedId, inputData.getIdentification());
    }
}
