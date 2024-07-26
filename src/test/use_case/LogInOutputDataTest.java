package use_case;

import org.junit.Test;
import use_case.Login.LoginOutputData;

import static org.junit.Assert.*;

public class LogInOutputDataTest {

    @Test
    public void testLogInOutputData() {
        LoginOutputData logInOutputData = new LoginOutputData("testUser", true);

        assertEquals("Username doesn't match", "testUser", logInOutputData.getIdentification());
        assertTrue("Success flag doesn't match", logInOutputData.isSuccess());
    }

    @Test
    public void testLogInOutputDataFailure() {
        LoginOutputData logInOutputData = new LoginOutputData("testUser", false);

        assertEquals("Username doesn't match", "testUser", logInOutputData.getIdentification());
        assertFalse("Success flag doesn't match", logInOutputData.isSuccess());
    }
}

