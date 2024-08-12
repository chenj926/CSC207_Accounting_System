package use_case.signup.SharedAccount;

import org.junit.jupiter.api.Test;
import use_case.signup.shared_account.SharedAccountSignupInputData;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SharedAccountSignupInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        Set<String> userIds = new HashSet<>();
        userIds.add("user1");
        userIds.add("user2");

        SharedAccountSignupInputData inputData = new SharedAccountSignupInputData("sharedAccount1", "sharedPassword123", userIds);

        assertEquals("sharedAccount1", inputData.getId());
        assertEquals("sharedPassword123", inputData.getPassword());
        assertEquals(userIds, inputData.getUserIds());
    }

    @Test
    public void testAddUserId() {
        Set<String> userIds = new HashSet<>();
        SharedAccountSignupInputData inputData = new SharedAccountSignupInputData("sharedAccount1", "sharedPassword123", userIds);

        inputData.addUserId("user3");

        assertTrue(inputData.getUserIds().contains("user3"));
    }

    @Test
    public void testRemoveUserId() {
        Set<String> userIds = new HashSet<>();
        userIds.add("user1");
        userIds.add("user2");

        SharedAccountSignupInputData inputData = new SharedAccountSignupInputData("sharedAccount1", "sharedPassword123", userIds);

        inputData.removeUserId("user1");

        assertTrue(!inputData.getUserIds().contains("user1"));
    }

    @Test
    public void testSetters() {
        Set<String> userIds = new HashSet<>();
        SharedAccountSignupInputData inputData = new SharedAccountSignupInputData("sharedAccount1", "sharedPassword123", userIds);

        inputData.setId("newSharedAccountId");
        assertEquals("newSharedAccountId", inputData.getId());

        inputData.setPassword("newSharedPassword");
        assertEquals("newSharedPassword", inputData.getPassword());
    }
}
