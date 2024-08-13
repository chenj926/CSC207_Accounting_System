package interface_adaptors.homepage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomepageTwoStateTest {

    private HomepageTwoState state;

    @BeforeEach
    void setUp() {
        state = new HomepageTwoState();
    }

    @Test
    void testGetAndSetId() {
        String id = "user123";
        state.setId(id);
        assertEquals(id, state.getId());
    }

    @Test
    void testGetAndSetTotalIncome() {
        String totalIncome = "1000.00";
        state.setTotalIncome(totalIncome);
        assertEquals(totalIncome, state.getTotalIncome());
    }

    @Test
    void testGetAndSetTotalOutflow() {
        String totalOutflow = "500.00";
        state.setTotalOutflow(totalOutflow);
        assertEquals(totalOutflow, state.getTotalOutflow());
    }

    @Test
    void testGetAndSetTotalBalance() {
        String totalBalance = "500.00";
        state.setTotalBalance(totalBalance);
        assertEquals(totalBalance, state.getTotalBalance());
    }

    @Test
    void testGetAndSetBasicUserInfo() {
        String[] basicUserInfo = {"user123", "user@example.com"};
        state.setBasicUserInfo(basicUserInfo);
        assertArrayEquals(basicUserInfo, state.getBasicUserInfo());
    }

    @Test
    void testGetAndSetErr() {
        String err = "An error occurred";
        state.setErr(err);
        assertEquals(err, state.getErr());
    }

    @Test
    void testInitialState() {
        assertNull(state.getId());
        assertNull(state.getTotalIncome());
        assertNull(state.getTotalOutflow());
        assertNull(state.getTotalBalance());
        assertNull(state.getBasicUserInfo());
        assertNull(state.getErr());
    }

    @Test
    void testSetAndGetAllFields() {
        String id = "user123";
        String totalIncome = "1000.00";
        String totalOutflow = "500.00";
        String totalBalance = "500.00";
        String[] basicUserInfo = {"user123", "user@example.com"};
        String err = "An error occurred";

        state.setId(id);
        state.setTotalIncome(totalIncome);
        state.setTotalOutflow(totalOutflow);
        state.setTotalBalance(totalBalance);
        state.setBasicUserInfo(basicUserInfo);
        state.setErr(err);

        assertEquals(id, state.getId());
        assertEquals(totalIncome, state.getTotalIncome());
        assertEquals(totalOutflow, state.getTotalOutflow());
        assertEquals(totalBalance, state.getTotalBalance());
        assertArrayEquals(basicUserInfo, state.getBasicUserInfo());
        assertEquals(err, state.getErr());
    }
}

