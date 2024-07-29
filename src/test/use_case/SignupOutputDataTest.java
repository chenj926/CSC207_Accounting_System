package use_case;

import org.junit.Test;
import use_case.signup.SignupOutputData;

import static org.junit.Assert.*;

public class SignupOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        SignupOutputData signupOutputData = new SignupOutputData("testUser", true);

        assertEquals("Username doesn't match", "testUser", signupOutputData.getUsername());
        assertTrue("useCaseFailed flag doesn't match", signupOutputData.isUseCaseFailed());
    }

    @Test
    public void testConstructorAndGettersUseCaseNotFailed() {
        SignupOutputData signupOutputData = new SignupOutputData("testUser", false);

        assertEquals("Username doesn't match", "testUser", signupOutputData.getUsername());
        assertFalse("useCaseFailed flag doesn't match", signupOutputData.isUseCaseFailed());
    }
}

