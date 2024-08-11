//package interface_adaptors;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import use_case.signup.SharedAccountSignupInputData;
//import use_case.signup.SharedAccountSignupInteractor;
//import use_case.signup.SignupInputData;
//import use_case.signup.SignupInteractor;
//
//
//class SignupControllerTest {
//    private SignupInteractor signupInteractor;
//    private SharedAccountSignupInteractor sharedAccountSignupInteractor;
//    private SignupController signupController;
//
//    @BeforeEach
//    void setUp() {
//        signupInteractor = Mockito.mock(SignupInteractor.class);
//        sharedAccountSignupInteractor = Mockito.mock(SharedAccountSignupInteractor.class);
//        signupController = new SignupController(signupInteractor, sharedAccountSignupInteractor);
//    }
//
//    @Test
//    void testExecuteWithSharedAccount() {
//        String username = "testuser";
//        String password = "testpassword";
//        String identification = "testid";
//        String sharedAccountId = "testsharedaccountid";
//
//        signupController.execute(username, password, identification, sharedAccountId);
//
//        verify(sharedAccountSignupInteractor).execute(any(SharedAccountSignupInputData.class));
//    }
//
//    @Test
//    void testExecuteWithoutSharedAccount() {
//        String username = "testuser";
//        String password = "testpassword";
//        String identification = "testid";
//        String sharedAccountId = null;
//
//        signupController.execute(username, password, identification, sharedAccountId);
//
//        verify(signupInteractor).execute(any(SignupInputData.class));
//    }
//}