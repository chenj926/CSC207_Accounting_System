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

    @Test
    void testSetters() {
        LogoutState logoutState = new LogoutState();

        String userId = "testUser";
        String logoutMessage = "Logout successful";
        boolean isLoggedOut = true;

        logoutState.setUserId(userId);
        logoutState.setLogoutMessage(logoutMessage);
        logoutState.setLoggedOut(isLoggedOut);

        Assertions.assertEquals(userId, logoutState.getUserId());
        Assertions.assertEquals(logoutMessage, logoutState.getLogoutMessage());
        Assertions.assertTrue(logoutState.isLoggedOut());
    }
}