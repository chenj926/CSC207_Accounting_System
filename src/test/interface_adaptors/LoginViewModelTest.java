package interface_adaptors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class LoginViewModelTest {

    private LoginViewModel loginViewModel;

    @BeforeEach
    void setUp() {
        loginViewModel = new LoginViewModel();
    }

    @Test
    void testGetters() {
        assertEquals("Log In View", loginViewModel.getTitleLabel());
        assertEquals("Enter username", loginViewModel.getUsernameLabel());
        assertEquals("Enter password", loginViewModel.getPasswordLabel());
        assertEquals("Log in", loginViewModel.getLoginButtonLabel());
        assertEquals("Cancel", loginViewModel.getCancelButtonLabel());
    }

    @Test
    void testSetState() {
        LoginState loginState = new LoginState();
        loginState.setUsername("testuser");
        loginState.setPassword("testpassword");

        loginViewModel.setState(loginState);
        assertEquals(loginState, loginViewModel.getState());
    }

}