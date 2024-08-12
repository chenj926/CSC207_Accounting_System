package interface_adaptors.signup;

import interface_adaptors.signup.user_account.UserAccountSignupController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.user_account.UserAccountSignupInputData;
import use_case.signup.user_account.UserAccountSignupInteractor;

import static org.mockito.Mockito.*;

class SignupControllerTest {
    private UserAccountSignupInteractor mockSignupInteractor;
    private UserAccountSignupController controller;

    @BeforeEach
    void setUp() {
        mockSignupInteractor = mock(UserAccountSignupInteractor.class);
        controller = new UserAccountSignupController(mockSignupInteractor);
    }

    @Test
    void testExecute() {
        String username = "testuser";
        String password = "testpassword";
        String identification = "testid";

        controller.execute(username, password, identification);

        UserAccountSignupInputData expectedInputData = new UserAccountSignupInputData(username, password, identification);
        verify(mockSignupInteractor, times(1)).execute(expectedInputData);
    }
}