package use_case.signup.SharedAccount;

import org.junit.jupiter.api.Test;
import use_case.signup.shared_account.SharedAccountSignupOutputData;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SharedAccountSignupOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        Set<String> userIds = new HashSet<>();
        userIds.add("user1");
        userIds.add("user2");

        SharedAccountSignupOutputData outputData = new SharedAccountSignupOutputData("sharedAccount1", userIds);

        assertEquals("sharedAccount1", outputData.getSharedAccountId());
        assertEquals(userIds, outputData.getUserIds());
    }

    @Test
    public void testEmptyUserIds() {
        Set<String> userIds = new HashSet<>();

        SharedAccountSignupOutputData outputData = new SharedAccountSignupOutputData("sharedAccount1", userIds);

        assertEquals("sharedAccount1", outputData.getSharedAccountId());
        assertEquals(0, outputData.getUserIds().size());
    }

    @Test
    public void testSingleUserId() {
        Set<String> userIds = new HashSet<>();
        userIds.add("user1");

        SharedAccountSignupOutputData outputData = new SharedAccountSignupOutputData("sharedAccount1", userIds);

        assertEquals("sharedAccount1", outputData.getSharedAccountId());
        assertEquals(1, outputData.getUserIds().size());
        assertEquals("user1", outputData.getUserIds().iterator().next());
    }
}
