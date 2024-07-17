package use_case;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginInputDataTest {

    private LoginInputData loginInputData;

    @Before
    public void setUp() {
        loginInputData = new LoginInputData("testUser", "testPass", "testID");
    }

    @Test
    public void testGetUsername() {
        assertEquals("Username doesn't match", "testUser", loginInputData.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("Password doesn't match", "testPass", loginInputData.getPassword());
    }

    @Test
    public void testGetIdentification() {
        assertEquals("Identification doesn't match", "testID", loginInputData.getIdentification());
    }
}

