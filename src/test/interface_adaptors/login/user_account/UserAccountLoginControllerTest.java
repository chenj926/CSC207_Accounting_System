package interface_adaptors.login.user_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.user_account.UserAccountLoginInputData;
import use_case.login.user_account.UserAccountLoginMediator;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountLoginControllerTest {

    private UserAccountLoginController controller;
    private TestUserAccountLoginMediator loginMediator;

    @BeforeEach
    void setUp() {
        loginMediator = new TestUserAccountLoginMediator();
        controller = new UserAccountLoginController(loginMediator);
    }

    @Test
    void testExecute() {
        String id = "user123";
        String password = "userPass";

        controller.execute(id, password);

        assertNotNull(loginMediator.inputData);
        assertEquals(id, loginMediator.inputData.getIdentification());
        assertEquals(password, loginMediator.inputData.getPassword());
    }

    // Inner class to simulate the UserAccountLoginMediator behavior
    private static class TestUserAccountLoginMediator extends UserAccountLoginMediator {

        private UserAccountLoginInputData inputData;

        public TestUserAccountLoginMediator() {
            super(null, null, null); // Passing nulls as we are focusing on just the login input data
        }

        @Override
        public void execute(UserAccountLoginInputData inputData) {
            this.inputData = inputData;  // Capture the input data for testing
        }
    }
}

