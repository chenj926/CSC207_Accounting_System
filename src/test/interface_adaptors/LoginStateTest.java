package interface_adaptors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoginStateTest {

    @Test
    void testInitialState() {
        LoginState loginState = new LoginState();

        assertEquals("", loginState.getUsername());
        assertNull(loginState.getUsernameError());
        assertEquals("", loginState.getPassword());
        assertNull(loginState.getPasswordError());
    }

    @Test
    void testSetUsername() {
        LoginState loginState = new LoginState();

        loginState.setUsername("testuser");
        assertEquals("testuser", loginState.getUsername());
        assertNull(loginState.getUsernameError());

        loginState.setUsername("");
        assertEquals("", loginState.getUsername());
        assertEquals("Username cannot be empty", loginState.getUsernameError());
    }

    @Test
    void testSetPassword() {
        LoginState loginState = new LoginState();

        loginState.setPassword("testpassword");
        assertEquals("testpassword", loginState.getPassword());
        assertNull(loginState.getPasswordError());

        loginState.setPassword("");
        assertEquals("", loginState.getPassword());
        assertEquals("Password cannot be empty", loginState.getPasswordError());
    }

}