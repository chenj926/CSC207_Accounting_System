package use_case.Login.UserAccount;

import org.junit.jupiter.api.Test;
import use_case.login.user_account.UserAccountLoginOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAccountLoginOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String userAccountId = "userUser123";
        boolean success = true;

        UserAccountLoginOutputData outputData = new UserAccountLoginOutputData(userAccountId, success);

        assertEquals(userAccountId, outputData.getIdentification());
        assertTrue(outputData.isSuccess());
    }

    @Test
    public void testConstructorAndGettersFailure() {
        String userAccountId = "sharedUser123";
        boolean success = false;

        UserAccountLoginOutputData outputData = new UserAccountLoginOutputData(userAccountId, success);

        assertEquals(userAccountId, outputData.getIdentification());
        assertEquals(false, outputData.isSuccess());
    }
}