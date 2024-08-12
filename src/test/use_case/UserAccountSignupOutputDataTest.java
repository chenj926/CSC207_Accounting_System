package use_case;

import org.junit.Test;
import use_case.signup.user_account.UserAccountSignupOutputData;

import static org.junit.Assert.*;

public class UserAccountSignupOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        UserAccountSignupOutputData userAccountSignupOutputData = new UserAccountSignupOutputData("testUser", true);

        assertEquals("Username doesn't match", "testUser", userAccountSignupOutputData.getUsername());
        assertTrue("useCaseFailed flag doesn't match", userAccountSignupOutputData.isUseCaseFailed());
    }

    @Test
    public void testConstructorAndGettersUseCaseNotFailed() {
        UserAccountSignupOutputData userAccountSignupOutputData = new UserAccountSignupOutputData("testUser", false);

        assertEquals("Username doesn't match", "testUser", userAccountSignupOutputData.getUsername());
        assertFalse("useCaseFailed flag doesn't match", userAccountSignupOutputData.isUseCaseFailed());
    }
}

