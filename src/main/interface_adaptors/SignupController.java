package interface_adaptors;

import use_case.SignupInputBoundary;
import use_case.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password, String identification) {
        SignupInputData signupInputData = new SignupInputData(
                username, password, identification);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}