package interface_adaptors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogoutStateTest {

    @Test
    void testDefaultConstructor() {
        LogoutState logoutState = new LogoutState();
        Assertions.assertEquals("", logoutState.getUserId());
        Assertions.assertEquals("confirm to logout", logoutState.getLogoutMessage());
        Assertions.assertFalse(logoutState.isLoggedOut());
    }

}