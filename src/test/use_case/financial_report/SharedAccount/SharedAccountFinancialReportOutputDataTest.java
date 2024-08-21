package use_case.financial_report.SharedAccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.financial_report.shared_account.SharedAccountFinancialReportOutputData;

public class SharedAccountFinancialReportOutputDataTest {
    @Test
    public void testConstructor() {
        String reportContent = "lalalala";
        SharedAccountFinancialReportOutputData outputData = new SharedAccountFinancialReportOutputData(reportContent);

        Assertions.assertEquals(reportContent, outputData.getReportContent());
    }
}
