package interface_adaptors.signup.shared_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountSignupViewModelTest {

    private SharedAccountSignupViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new SharedAccountSignupViewModel();
    }

    @Test
    void testInitialState() {
        // Test that the initial state is set correctly
        SharedAccountSignupState state = viewModel.getState();
        assertNotNull(state);
        assertTrue(state.getUserId().isEmpty());
        assertNull(state.getSuccessMsg());
    }

    @Test
    void testTitleLabel() {
        // Test that the title label is returned correctly
        assertEquals("Shared Account Sign Up", viewModel.getTitleLabel());
    }

    @Test
    void testIdentificationLabel() {
        // Test that the identification label is returned correctly
        assertEquals("Set shared account id", viewModel.getID_LABEL());
    }

    @Test
    void testPasswordLabel() {
        // Test that the password label is returned correctly
        assertEquals("Set shared account password", viewModel.getPasswordLabel());
    }

    @Test
    void testUserIdsLabel() {
        // Test that the user IDs label is returned correctly
        assertEquals("Add users", viewModel.getUserIdsLabel());
    }

    @Test
    void testViewName() {
        // Test that the view name is set correctly
        assertEquals("shared account sign up", viewModel.getViewName());
    }

    @Test
    void testAddMoreUserLabel() {
        // Test adding additional user labels
        viewModel.addMoreUserLabel();
        assertEquals(1, viewModel.getAdditionalUserLabels().size());
        assertEquals("Add user0 ID", viewModel.getAdditionalUserLabels().get(0));

        viewModel.addMoreUserLabel();
        assertEquals(2, viewModel.getAdditionalUserLabels().size());
        assertEquals("Add user1 ID", viewModel.getAdditionalUserLabels().get(1));
    }

    @Test
    void testRemoveLastUserLabel() {
        // Test removing the last additional user label
        viewModel.addMoreUserLabel();
        viewModel.addMoreUserLabel();
        viewModel.removeLastUserLabel();
        assertEquals(1, viewModel.getAdditionalUserLabels().size());
        assertEquals("Add user0 ID", viewModel.getAdditionalUserLabels().get(0));
    }

    @Test
    void testSetState() {
        // Test setting and getting the state
        SharedAccountSignupState newState = new SharedAccountSignupState();
        newState.addUserId("user1");
        newState.setSuccessMsg("Success!");

        viewModel.setState(newState);

        assertEquals(Set.of("user1"), viewModel.getState().getUserId());
        assertEquals("Success!", viewModel.getState().getSuccessMsg());
    }

    @Test
    void testFirePropertyChanged() {
        // Ensure firePropertyChanged doesn't cause errors (assuming you don't need to check specific listeners)
        assertDoesNotThrow(() -> viewModel.firePropertyChanged());
    }
}


