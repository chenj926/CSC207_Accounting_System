package interface_adaptors.signup;

import use_case.signup.SharedAccountSignupInputData;
import use_case.signup.SharedAccountSignupInteractor;
import use_case.signup.SignupInteractor;
import use_case.signup.*;

public class SharedAccountSignupController extends SignupController {
    final SharedAccountSignupInputBoundary sharedAccountSignupInteractor;

    public SharedAccountSignupController(SignupInteractor signupInteractor,
                                         SharedAccountSignupInputBoundary sharedAccountSignupInteractor) {
        super(signupInteractor);
        this.sharedAccountSignupInteractor = sharedAccountSignupInteractor;
    }

    public void execute(String username, String password, String identification, String sharedAccountId) {
        SharedAccountSignupInputData sharedAccountSignupInputData = new SharedAccountSignupInputData(username,
                password, identification, sharedAccountId);

        sharedAccountSignupInteractor.execute(sharedAccountSignupInputData);

    }
}
