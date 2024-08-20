package interface_adaptors.signup.shared_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountSignupStateTest {

    private SharedAccountSignupState state;

    @BeforeEach
    void setUp() {
        state = new SharedAccountSignupState();
    }

    @Test
    void testInitialState() {
        // Test that the initial state is set correctly
        assertTrue(state.getUserId().isEmpty());
        assertNull(state.getSuccessMsg());
    }

    @Test
    void testAddUserId() {
        // Test adding user IDs
        state.addUserId("user1");
        assertEquals(Set.of("user1"), state.getUserId());

        state.addUserId("user2");
        assertEquals(Set.of("user1", "user2"), state.getUserId());
    }

    @Test
    void testRemoveUserId() {
        // Test removing user IDs
        state.addUserId("user1");
        state.addUserId("user2");
        state.removeUserId("user1");
        assertEquals(Set.of("user2"), state.getUserId());
    }

    @Test
    void testSetUserIds() {
        // Test setting user IDs
        Set<String> userIds = new HashSet<>();
        userIds.add("user1");
        userIds.add("user2");

        state.setUserIds(userIds);
        assertEquals(userIds, state.getUserId());
    }

    @Test
    void testClearUserIds() {
        // Test clearing all user IDs
        state.addUserId("user1");
        state.addUserId("user2");
        state.clearUserIds();
        assertTrue(state.getUserId().isEmpty());
    }

    @Test
    void testIsComplete() {
        // Test checking if the state is complete
        assertFalse(state.isComplete());

        state.setSuccessMsg("Success!");
        assertTrue(state.isComplete());
    }

    @Test
    void testReset() {
        // Test resetting the state
        state.addUserId("user1");
        state.setSuccessMsg("Success!");

        state.reset();

        assertTrue(state.getUserId().isEmpty());
        assertNull(state.getSuccessMsg());
    }
}

