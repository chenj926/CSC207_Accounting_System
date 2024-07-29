package interface_adaptors.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;


/**
 * The SignupController class is responsible for handling user interactions related to the signup process.
 * It communicates with the use case interactor to execute the signup process.
 *
 * @author Rita
 * @author Eric
 */
public class SignupController {
    final SignupInputBoundary userSignupUseCaseInteractor;

    /**
     * Constructs a SignupController object with the specified use case interactor.
     *
     * @param userSignupUseCaseInteractor the use case interactor for user signup
     */
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the signup process with the given user details.
     *
     * @param username       the username of the user
     * @param password       the password of the user
     * @param identification the identification of the user
     */
    public void execute(String username, String password, String identification) {
        SignupInputData signupInputData = new SignupInputData(
                username, password, identification);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}