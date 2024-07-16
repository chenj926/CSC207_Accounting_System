package entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SharedAccountTest {

    private SharedAccount sharedAccount;

    @Before
    public void setUp() {
        sharedAccount = new SharedAccount("sharedTestID", "irrelevantPassword");
    }

    @Test
    public void testInitialValues() {
        assertEquals("Identification doesn't match", "sharedTestID", sharedAccount.getIdentification());
        assertTrue("Shared user identifications should initially be empty", sharedAccount.getSharedUserIdentifications().isEmpty());
    }

    @Test
    public void testAddUserIdentification() {
        sharedAccount.addUserIdentification("user1");
        sharedAccount.addUserIdentification("user2");

        Set<String> sharedUsers = sharedAccount.getSharedUserIdentifications();
        assertEquals("There should be 2 shared user identifications", 2, sharedUsers.size());
        assertTrue("Shared user identifications should contain 'user1'", sharedUsers.contains("user1"));
        assertTrue("Shared user identifications should contain 'user2'", sharedUsers.contains("user2"));
    }

    @Test
    public void testRemoveUserIdentification() {
        sharedAccount.addUserIdentification("user1");
        sharedAccount.addUserIdentification("user2");

        sharedAccount.removeUserIdentification("user1");

        Set<String> sharedUsers = sharedAccount.getSharedUserIdentifications();
        assertEquals("There should be 1 shared user identification", 1, sharedUsers.size());
        assertFalse("Shared user identifications should not contain 'user1'", sharedUsers.contains("user1"));
        assertTrue("Shared user identifications should contain 'user2'", sharedUsers.contains("user2"));
    }
}
