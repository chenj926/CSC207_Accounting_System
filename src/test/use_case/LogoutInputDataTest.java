package use_case;

import org.junit.Test;
import static org.junit.Assert.*;

public class LogoutInputDataTest {

    @Test
    public void testLogoutInputData() {
        LogoutInputData logoutInputData = new LogoutInputData("logoutUser");

        assertEquals("Logout identifier doesn't match", "logoutUser", logoutInputData.getLogout());
    }
}
