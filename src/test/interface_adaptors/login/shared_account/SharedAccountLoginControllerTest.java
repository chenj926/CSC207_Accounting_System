package interface_adaptors.login.shared_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.shared_account.SharedAccountLoginInputData;
import use_case.login.shared_account.SharedAccountLoginMediator;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountLoginControllerTest {

    private SharedAccountLoginController controller;
    private TestSharedAccountLoginMediator loginMediator;

    @BeforeEach
    void setUp() {
        loginMediator = new TestSharedAccountLoginMediator();
        controller = new SharedAccountLoginController(loginMediator);
    }

    @Test
    void testExecute() {
        String identification = "shared123";
        String password = "sharedPass";

        controller.execute(identification, password);

        assertNotNull(loginMediator.inputData);
        assertEquals(identification, loginMediator.inputData.getIdentification());
        assertEquals(password, loginMediator.inputData.getPassword());
    }

    // Inner class to simulate the SharedAccountLoginMediator behavior
    private static class TestSharedAccountLoginMediator extends SharedAccountLoginMediator {

        private SharedAccountLoginInputData inputData;

        public TestSharedAccountLoginMediator() {
            super(null, null, null); // Passing nulls as we are focusing on just the login input data
        }

        @Override
        public void execute(SharedAccountLoginInputData inputData) {
            this.inputData = inputData;  // Capture the input data for testing
        }
    }
}


