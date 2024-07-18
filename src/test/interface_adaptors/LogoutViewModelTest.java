package interface_adaptors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
}