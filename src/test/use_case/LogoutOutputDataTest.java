package use_case;

import org.junit.Test;
import static org.junit.Assert.*;

public class LogoutOutputDataTest {

    @Test
    public void testConstructorAndGetter() {
        LogoutOutputData logoutOutputData = new LogoutOutputData("initialFailMessage");

        assertEquals("Fail message doesn't match", "initialFailMessage", logoutOutputData.getFail());
    }

    @Test
    public void testSetter() {
        LogoutOutputData logoutOutputData = new LogoutOutputData("initialFailMessage");
        logoutOutputData.setFail("newFailMessage");

        assertEquals("Fail message doesn't match after setting new value", "newFailMessage", logoutOutputData.getFail());
    }
}

