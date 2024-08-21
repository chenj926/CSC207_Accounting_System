package use_case.Login.UserAccount;

import org.junit.jupiter.api.Test;
import use_case.login.user_account.UserAccountLoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountLoginInputDataTest {
    @Test
    public void testConstructorAndGetters() {
        String userAccountId = "userUser123";
        String userPassword = "userPassword123";
        UserAccountLoginInputData inputData = new UserAccountLoginInputData(userAccountId, userPassword);

        assertEquals(userAccountId, inputData.getIdentification());
        assertEquals(userPassword, inputData.getPassword());
    }
}
