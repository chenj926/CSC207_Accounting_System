package interface_adaptors.homepage;

import interface_adaptors.homepage.user_account.UserAccountHomepageTwoState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountHomePageTwoStateTest {

    private UserAccountHomepageTwoState state;

    @BeforeEach
    void setUp() {
        state = new UserAccountHomepageTwoState();
    }

    @Test
    void testSetAndGetUsername() {
        String username = "testUser";
        state.setUsername(username);
        assertEquals(username, state.getUsername());
    }

    @Test
    void testSetAndGetTotalIncome() {
        String totalIncome = "2000.00";
        state.setTotalIncome(totalIncome);
        assertEquals(totalIncome, state.getTotalIncome());
    }

    @Test
    void testSetAndGetTotalOutflow() {
        String totalOutflow = "1000.00";
        state.setTotalOutflow(totalOutflow);
        assertEquals(totalOutflow, state.getTotalOutflow());
    }

    @Test
    void testSetAndGetTotalBalance() {
        String totalBalance = "1000.00";
        state.setTotalBalance(totalBalance);
        assertEquals(totalBalance, state.getTotalBalance());
    }

    @Test
    void testSetAndGetBasicUserInfo() {
        String[] basicUserInfo = {"testUser", "Basic Info"};
        state.setBasicUserInfo(basicUserInfo);
        assertArrayEquals(basicUserInfo, state.getBasicUserInfo());
    }

    @Test
    void testSetAndGetId() {
        String id = "12345";
        state.setId(id);
        assertEquals(id, state.getId());
    }

    @Test
    void testSetAndGetErr() {
        String err = "An error occurred";
        state.setErr(err);
        assertEquals(err, state.getErr());
    }
}
