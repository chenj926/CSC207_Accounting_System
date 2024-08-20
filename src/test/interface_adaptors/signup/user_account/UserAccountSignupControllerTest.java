package interface_adaptors.signup.user_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.user_account.UserAccountSignupInputData;
import use_case.signup.user_account.UserAccountSignupInteractor;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountSignupControllerTest {

    private UserAccountSignupController controller;
    private TestUserAccountSignupInteractor signupInteractor;

    @BeforeEach
    void setUp() {
        signupInteractor = new TestUserAccountSignupInteractor();
        controller = new UserAccountSignupController(signupInteractor);
    }

    @Test
    void testExecute() {
        String username = "testUser";
        String password = "testPass";
        String identification = "user123";

        controller.execute(username, password, identification);

        // Validate that the interactor received the correct data
        assertNotNull(signupInteractor.inputData);
        assertEquals(username, signupInteractor.inputData.getUsername());
        assertEquals(password, signupInteractor.inputData.getPassword());
        assertEquals(identification, signupInteractor.inputData.getId());
    }

    // Inner class to simulate the UserAccountSignupInteractor behavior
    private static class TestUserAccountSignupInteractor extends UserAccountSignupInteractor {

        private UserAccountSignupInputData inputData;

        public TestUserAccountSignupInteractor() {
            // Pass nulls for the required arguments as we are focusing on just the signup input data
            super(null, null, null);
        }

        @Override
        public void execute(UserAccountSignupInputData inputData) {
            this.inputData = inputData;  // Capture the input data for testing
        }
    }
}

