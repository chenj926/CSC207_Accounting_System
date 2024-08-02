package interface_adaptors.signup;

import use_case.signup.*;
//import use_case.signup.StandardSignupInteractor;

/**
 * The SignupController class is responsible for handling user interactions related to the signup process.
 * It communicates with the use case interactor to execute the signup process.
 *
 * @author Rita
 * @author Eric
 */
public class SignupController {
    final SignupInteractor signupInteractor;
    final SharedAccountSignupInteractor sharedAccountSignupInteractor;

    /**
     * Constructs a SignupController object with the specified use case interactors.
     *
     * @param standardSignupInteractor     the use case interactor for standard user signup
     * @param sharedAccountSignupInteractor the use case interactor for shared account signup
     */
    public SignupController(SignupInteractor standardSignupInteractor, SharedAccountSignupInteractor sharedAccountSignupInteractor) {
        this.signupInteractor = standardSignupInteractor;
        this.sharedAccountSignupInteractor = sharedAccountSignupInteractor;
    }

    /**
     * Executes the signup process with the given user details.
     *
     * @param username        the username of the user
     * @param password        the password of the user
     * @param identification  the identification of the user
     * @param sharedAccountId the shared account ID (optional, can be null)
     */
    public void execute(String username, String password, String identification, String sharedAccountId) {
        if (sharedAccountId != null && !sharedAccountId.isEmpty()) {
            SharedAccountSignupInputData signupInputData = new SharedAccountSignupInputData(
                    username, password, identification, sharedAccountId);
            sharedAccountSignupInteractor.execute(signupInputData);
        } else {
            SignupInputData signupInputData = new SignupInputData(
                    username, password, identification);
            signupInteractor.execute(signupInputData);
        }
    }
}
