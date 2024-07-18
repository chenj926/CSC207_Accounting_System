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

}