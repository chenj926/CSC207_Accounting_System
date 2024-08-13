package interface_adaptors.login.user_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountLoginViewModelTest {

    private UserAccountLoginViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new UserAccountLoginViewModel();
    }

    @Test
    void testInitialState() {
        // Test that the initial state is set correctly
        UserAccountLoginState state = viewModel.getState();
        assertNotNull(state);
        assertEquals("", state.getIdentification());
        assertNull(state.getSuccessMsg());
    }

    @Test
    void testTitleLabel() {
        // Test that the title label is returned correctly
        assertEquals("ACCOUNT LOGIN", viewModel.getTitleLabel());
    }

    @Test
    void testIdentificationLabel() {
        // Test that the identification label is returned correctly
        assertEquals("Enter account ID", viewModel.getIdentificationLabel());
    }

    @Test
    void testPasswordLabel() {
        // Test that the password label is returned correctly
        assertEquals("Enter account password", viewModel.getPasswordLabel());
    }

    @Test
    void testViewName() {
        // Test that the view name is set correctly
        assertEquals("log in", viewModel.getViewName());
    }

    @Test
    void testSetState() {
        // Test setting and getting the state
        UserAccountLoginState newState = new UserAccountLoginState();
        newState.setIdentification("testId");
        newState.setSuccessMsg("Success!");

        viewModel.setState(newState);

        assertEquals("testId", viewModel.getState().getIdentification());
        assertEquals("Success!", viewModel.getState().getSuccessMsg());
    }

    @Test
    void testFirePropertyChanged() {
        // Ensure firePropertyChanged doesn't cause errors (assuming you don't need to check specific listeners)
        assertDoesNotThrow(() -> viewModel.firePropertyChanged());
    }
}

