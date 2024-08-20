package interface_adaptors.signup.user_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountSignupStateTest {

    private UserAccountSignupState state;

    @BeforeEach
    void setUp() {
        state = new UserAccountSignupState();
    }

    @Test
    void testInitialState() {
        // Test that the initial state is set correctly
        assertEquals("", state.getUsername());
        assertNull(state.getSuccessMsg());
    }

    @Test
    void testSetUsername() {
        // Test setting the username
        state.setUsername("testUser");
        assertEquals("testUser", state.getUsername());
    }

    @Test
    void testReset() {
        // Test resetting the state
        state.setUsername("testUser");
        state.setSuccessMsg("Success!");

        state.reset();

        assertEquals("", state.getUsername());
        assertNull(state.getSuccessMsg());
    }
}

