package interface_adaptors.signup.user_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountSignupViewModelTest {

    private UserAccountSignupViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountSignupViewModel();
    }

    @Test
    void testInitialState() {
        // Test that the initial state is set correctly
        UserAccountSignupState state = viewModel.getState();
        assertNotNull(state);
        assertEquals("", state.getUsername());
        assertNull(state.getSuccessMsg());
    }

    @Test
    void testTitleLabel() {
        // Test that the title label is returned correctly
        assertEquals("Sign Up", viewModel.getTitleLabel());
    }

    @Test
    void testUsernameLabel() {
        // Test that the username label is returned correctly
        assertEquals("Set username", viewModel.getUsernameLabel());
    }

    @Test
    void testPasswordLabel() {
        // Test that the password label is returned correctly
        assertEquals("Set password", viewModel.getPasswordLabel());
    }

    @Test
    void testIdentificationLabel() {
        // Test that the identification label is returned correctly
        assertEquals("Set identification", viewModel.getID_LABEL());
    }

    @Test
    void testViewName() {
        // Test that the view name is set correctly
        assertEquals("sign up", viewModel.getViewName());
    }

    @Test
    void testSetState() {
        // Test setting and getting the state
        UserAccountSignupState newState = new UserAccountSignupState();
        newState.setUsername("testUser");
        newState.setSuccessMsg("Success!");

        viewModel.setState(newState);

        assertEquals("testUser", viewModel.getState().getUsername());
        assertEquals("Success!", viewModel.getState().getSuccessMsg());
    }

    @Test
    void testFirePropertyChanged() {
        // Ensure firePropertyChanged doesn't cause errors (assuming you don't need to check specific listeners)
        assertDoesNotThrow(() -> viewModel.firePropertyChanged());
    }
}

