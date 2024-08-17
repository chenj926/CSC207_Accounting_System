package use_case.Login.SharedAccount;

import org.junit.jupiter.api.Test;
import use_case.login.shared_account.SharedAccountLoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SharedAccountLoginInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String sharedAccountId = "sharedUser123";
        String sharedPassword = "sharedPassword123";
        SharedAccountLoginInputData inputData = new SharedAccountLoginInputData(sharedAccountId, sharedPassword);

        assertEquals(sharedAccountId, inputData.getIdentification());
        assertEquals(sharedPassword, inputData.getPassword());
    }
}
