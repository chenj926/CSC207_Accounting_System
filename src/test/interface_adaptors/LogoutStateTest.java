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

    @Test
    void testCopyConstructor() {
        LogoutState originalState = new LogoutState();
        originalState.setUserId("testUser");
        originalState.setLogoutMessage("Logout successful");
        originalState.setLoggedOut(true);

        LogoutState copiedState = new LogoutState(originalState);

        Assertions.assertEquals(originalState.getUserId(), copiedState.getUserId());
        Assertions.assertEquals(originalState.getLogoutMessage(), copiedState.getLogoutMessage());
        Assertions.assertEquals(originalState.isLoggedOut(), copiedState.isLoggedOut());
    }

}