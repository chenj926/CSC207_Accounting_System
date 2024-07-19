package use_case;

import org.junit.Test;
import static org.junit.Assert.*;

public class LogInOutputDataTest {

    @Test
    public void testLogInOutputData() {
        LoginOutputData logInOutputData = new LoginOutputData("testUser", true);

        assertEquals("Username doesn't match", "testUser", logInOutputData.getUsername());
        assertTrue("Success flag doesn't match", logInOutputData.isSuccess());
    }

    @Test
    public void testLogInOutputDataFailure() {
        LoginOutputData logInOutputData = new LoginOutputData("testUser", false);

        assertEquals("Username doesn't match", "testUser", logInOutputData.getUsername());
        assertFalse("Success flag doesn't match", logInOutputData.isSuccess());
    }
}

