package interface_adaptors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignupStateTest {

    private SignupState signupState;

    @BeforeEach
    void setUp() {
        signupState = new SignupState();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals("", signupState.getIdentification());
        Assertions.assertNull(signupState.getIdentificationError());
        Assertions.assertEquals("", signupState.getUsername());
        Assertions.assertNull(signupState.getUsernameError());
        Assertions.assertEquals("", signupState.getPassword());
        Assertions.assertNull(signupState.getPasswordError());
    }

    @Test
    void testSetters() {
        signupState.setIdentification("testIdentification");
        signupState.setIdentificationError("testIdentificationError");
        signupState.setUsername("testUsername");
        signupState.setUsernameError("testUsernameError");
        signupState.setPassword("testPassword");
        signupState.setPasswordError("testPasswordError");

        Assertions.assertEquals("testIdentification", signupState.getIdentification());
        Assertions.assertEquals("testIdentificationError", signupState.getIdentificationError());
        Assertions.assertEquals("testUsername", signupState.getUsername());
        Assertions.assertEquals("testUsernameError", signupState.getUsernameError());
        Assertions.assertEquals("testPassword", signupState.getPassword());
        Assertions.assertEquals("testPasswordError", signupState.getPasswordError());
    }

    @Test
    void testIsValid() {
        signupState.setUsername("testUsername");
        signupState.setUsernameError(null);
        signupState.setPassword("testPassword");
        signupState.setPasswordError(null);
        Assertions.assertTrue(signupState.isValid());

        signupState.setUsernameError("testUsernameError");
        Assertions.assertFalse(signupState.isValid());

        signupState.setUsernameError(null);
        signupState.setPasswordError("testPasswordError");
        Assertions.assertFalse(signupState.isValid());
    }

    @Test
    void testReset() {
        signupState.setIdentification("testIdentification");
        signupState.setIdentificationError("testIdentificationError");
        signupState.setUsername("testUsername");
        signupState.setUsernameError("testUsernameError");
        signupState.setPassword("testPassword");
        signupState.setPasswordError("testPasswordError");

        signupState.reset();

        Assertions.assertEquals("", signupState.getIdentification());
        Assertions.assertNull(signupState.getIdentificationError());
        Assertions.assertEquals("", signupState.getUsername());
        Assertions.assertNull(signupState.getUsernameError());
        Assertions.assertEquals("", signupState.getPassword());
        Assertions.assertNull(signupState.getPasswordError());
    }
}