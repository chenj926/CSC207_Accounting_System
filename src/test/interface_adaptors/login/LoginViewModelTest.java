package interface_adaptors.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class LoginViewModelTest {
    private PropertyChangeListener mockListener;

    private LoginViewModel viewModel;

    @BeforeEach
    void setup() {

        // Create the view model
        viewModel = new LoginViewModel();
    }

    @Test
    void testPropertyChange() {
        // Add the mock listener to the view model
        viewModel.addPropertyChangeListener(mockListener);

        // Trigger a property change
        viewModel.setUsername("testuser");

    }

    private static class LoginViewModel {
        private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }

        public void setUsername(String username) {
            String oldUsername = this.username;
            this.username = username;
            propertyChangeSupport.firePropertyChange("username", oldUsername, username);
        }

        private String username;
    }
}