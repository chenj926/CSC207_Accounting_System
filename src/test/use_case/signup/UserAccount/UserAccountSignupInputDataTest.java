package use_case.signup.UserAccount;

import org.junit.Test;
import use_case.signup.user_account.UserAccountSignupInputData;

import static org.junit.Assert.*;

public class UserAccountSignupInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        UserAccountSignupInputData userAccountSignupInputData = new UserAccountSignupInputData("testUser", "testPass", "testID");

        assertEquals("Username doesn't match", "testUser", userAccountSignupInputData.getUsername());
        assertEquals("Password doesn't match", "testPass", userAccountSignupInputData.getPassword());
    }
}

