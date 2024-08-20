package interface_adaptors.login.shared_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountLoginViewModelTest {

    private SharedAccountLoginViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountLoginViewModel();
    }

    @Test
    void testInitialState() {
        // Test that the initial state is set correctly
        SharedAccountLoginState state = viewModel.getState();
        assertNotNull(state);
        assertEquals("", state.getIdentification());
        assertNull(state.getSuccessMsg());
    }

    @Test
    void testTitleLabel() {
        // Test that the title label is returned correctly
        assertEquals("SHARED ACCOUNT LOGIN", viewModel.getTitleLabel());
    }

    @Test
    void testIdentificationLabel() {
        // Test that the identification label is returned correctly
        assertEquals("Enter shared account ID", viewModel.getIdentificationLabel());
    }

    @Test
    void testPasswordLabel() {
        // Test that the password label is returned correctly
        assertEquals("Enter shared account password", viewModel.getPasswordLabel());
    }

    @Test
    void testViewName() {
        // Test that the view name is set correctly
        assertEquals("shared account log in", viewModel.getViewName());
    }

    @Test
    void testSetState() {
        // Test setting and getting the state
        SharedAccountLoginState newState = new SharedAccountLoginState();
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

