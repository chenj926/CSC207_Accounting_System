package interface_adaptors.signup.shared_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.shared_account.SharedAccountSignupInputData;
import use_case.signup.shared_account.SharedAccountSignupInteractor;

import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountSignupControllerTest {

    private SharedAccountSignupController controller;
    private TestSharedAccountSignupInteractor signupInteractor;

    @BeforeEach
    void setUp() {
        signupInteractor = new TestSharedAccountSignupInteractor();
        controller = new SharedAccountSignupController(signupInteractor);
    }

    @Test
    void testExecute() {
        String sharedAccountId = "sharedAccount123";
        String sharedAccountPassword = "sharedPass123";
        Set<String> userIds = new HashSet<>(Set.of("user1", "user2", "user3"));

        controller.execute(sharedAccountId, sharedAccountPassword, userIds);

        // Validate that the interactor received the correct data
        assertNotNull(signupInteractor.inputData);
        assertEquals(sharedAccountId, signupInteractor.inputData.getId());
        assertEquals(sharedAccountPassword, signupInteractor.inputData.getPassword());
        assertEquals(userIds, signupInteractor.inputData.getUserIds());
    }

    // Inner class to simulate the SharedAccountSignupInteractor behavior
    private static class TestSharedAccountSignupInteractor extends SharedAccountSignupInteractor {

        private SharedAccountSignupInputData inputData;

        public TestSharedAccountSignupInteractor() {
            // Pass nulls for the required arguments as we are focusing on just the signup input data
            super(null, null, null, null);
        }

        @Override
        public void execute(SharedAccountSignupInputData inputData) {
            this.inputData = inputData;  // Capture the input data for testing
        }
    }
}


