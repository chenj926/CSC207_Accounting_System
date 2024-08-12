package use_case.Login.SharedAccount;

import org.junit.jupiter.api.Test;
import use_case.login.shared_account.SharedAccountLoginOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SharedAccountLoginOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String sharedAccountId = "sharedUser123";
        boolean success = true;

        SharedAccountLoginOutputData outputData = new SharedAccountLoginOutputData(sharedAccountId, success);

        assertEquals(sharedAccountId, outputData.getIdentification());
        assertTrue(outputData.isSuccess());
    }

    @Test
    public void testConstructorAndGettersFailure() {
        String sharedAccountId = "sharedUser123";
        boolean success = false;

        SharedAccountLoginOutputData outputData = new SharedAccountLoginOutputData(sharedAccountId, success);

        assertEquals(sharedAccountId, outputData.getIdentification());
        assertEquals(false, outputData.isSuccess());
    }
}
