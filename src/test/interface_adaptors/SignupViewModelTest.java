package interface_adaptors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class SignupViewModelTest {

    private SignupViewModel signupViewModel;

    @BeforeEach
    void setUp() {
        signupViewModel = new SignupViewModel();
    }

    @Test
    void testGetters() {
        Assertions.assertEquals("Sign Up View", signupViewModel.getTitleLabel());
        Assertions.assertEquals("Set username", signupViewModel.getUsernameLabel());
        Assertions.assertEquals("Set password", signupViewModel.getPasswordLabel());
        Assertions.assertEquals("Set identification", signupViewModel.getID_LABEL());
        Assertions.assertEquals("Sign up", signupViewModel.getSignupButtonLabel());
        Assertions.assertEquals("Cancel", signupViewModel.getCancelButtonLabel());
    }

    @Test
    void testState() {
        SignupState state = new SignupState();
        state.setUsername("testUsername");
        state.setPassword("testPassword");
        state.setIdentification("testIdentification");

        signupViewModel.setState(state);
        Assertions.assertEquals(state, signupViewModel.getState());
    }

    @Test
    void testPropertyChangeSupport() {
        SignupState oldState = signupViewModel.getState();
        SignupState newState = new SignupState();
        newState.setUsername("testUsername");

        PropertyChangeListener listener = this::propertyChangedHandler;
        signupViewModel.addPropertyChangeListener(listener);

        signupViewModel.setState(newState);
        signupViewModel.firePropertyChanged();

        Assertions.assertNotEquals(oldState, signupViewModel.getState());
        Assertions.assertEquals(newState, signupViewModel.getState());
    }

    private void propertyChangedHandler(PropertyChangeEvent event) {
        Assertions.assertEquals("state", event.getPropertyName());
        Assertions.assertNotNull(event.getNewValue());
        Assertions.assertInstanceOf(SignupState.class, event.getNewValue());
    }
}