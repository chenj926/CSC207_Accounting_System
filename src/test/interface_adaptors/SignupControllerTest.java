package interface_adaptors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.SignupInputBoundary;
import use_case.SignupInputData;

class SignupControllerTest {

    @Test
    void testExecute() {
        // Arrange
        String username = "testUsername";
        String password = "testPassword";
        String identification = "testIdentification";

        SignupInputBoundary mockSignupInputBoundary = new MockSignupInputBoundary();
        SignupController signupController = new SignupController(mockSignupInputBoundary);

        // Act
        signupController.execute(username, password, identification);

        // Assert
        SignupInputData expectedSignupInputData = new SignupInputData(username, password, identification);
        ((MockSignupInputBoundary) mockSignupInputBoundary).assertExecuteCalledWithExpectedData(expectedSignupInputData);
    }


    private static class MockSignupInputBoundary implements SignupInputBoundary {
        private SignupInputData executedSignupInputData;

        @Override
        public void execute(SignupInputData signupInputData) {
            this.executedSignupInputData = signupInputData;
        }

        public void assertExecuteCalledWithExpectedData(SignupInputData expectedSignupInputData) {
            Assertions.assertEquals(expectedSignupInputData.getUsername(), executedSignupInputData.getUsername());
            Assertions.assertEquals(expectedSignupInputData.getPassword(), executedSignupInputData.getPassword());
            Assertions.assertEquals(expectedSignupInputData.getIdentification(), executedSignupInputData.getIdentification());
        }
    }
}