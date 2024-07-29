package use_case;

import org.junit.Test;
import use_case.signup.SignupInputData;

import static org.junit.Assert.*;

public class SignupInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        SignupInputData signupInputData = new SignupInputData("testUser", "testPass", "testID");

        assertEquals("Username doesn't match", "testUser", signupInputData.getUsername());
        assertEquals("Password doesn't match", "testPass", signupInputData.getPassword());
        assertEquals("Identification doesn't match", "testID", signupInputData.getIdentification());
    }
}

