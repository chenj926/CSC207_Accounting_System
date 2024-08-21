package use_case.financial_report.UserAccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.financial_report.user_account.UserAccountFinancialReportOutputData;


public class UserAccountFinancialReportOutputDataTest {
    @Test
    public void testConstructor() {
        String reportContent = "lalalala";
        UserAccountFinancialReportOutputData outputData = new UserAccountFinancialReportOutputData(reportContent);

        Assertions.assertEquals(reportContent, outputData.getReportContent());
    }
}
