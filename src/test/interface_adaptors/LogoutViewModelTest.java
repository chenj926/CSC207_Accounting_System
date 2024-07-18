package interface_adaptors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class LogoutViewModelTest {

    private LogoutViewModel logoutViewModel;

    @BeforeEach
    void setUp() {
        logoutViewModel = new LogoutViewModel();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals("LogoutView", logoutViewModel.getTitleLabel());
        Assertions.assertEquals("Logout", logoutViewModel.getLogoutButtonText());
        Assertions.assertEquals("Cancel", logoutViewModel.getCancelButtonText());
        Assertions.assertNotNull(logoutViewModel.getState());
    }

    @Test
    void testSetState() {
        LogoutState newState = new LogoutState();
        newState.setUserId("testUser");
        newState.setLogoutMessage("Logout successful");
        newState.setLoggedOut(true);

        logoutViewModel.setState(newState);

        Assertions.assertEquals(newState, logoutViewModel.getState());
    }

    @Test
    void testFirePropertyChanged() {
        LogoutState newState = new LogoutState();
        newState.setUserId("testUser");
        newState.setLogoutMessage("Logout successful");
        newState.setLoggedOut(true);

        PropertyChangeListener listener = this::propertyChangeHandler;
        logoutViewModel.addPropertyChangeListener(listener);

        logoutViewModel.setState(newState);
        logoutViewModel.firePropertyChanged();
    }

    private void propertyChangeHandler(PropertyChangeEvent event) {
        Assertions.assertEquals("state", event.getPropertyName());
        Assertions.assertNotNull(event.getNewValue());
        Assertions.assertTrue(event.getNewValue() instanceof LogoutState);
    }
}