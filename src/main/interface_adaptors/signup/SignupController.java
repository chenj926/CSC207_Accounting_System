package interface_adaptors.signup;

import use_case.signup.*;

/**
 * The SignupController class is responsible for handling user interactions related to the signup process.
 * It communicates with the use case interactor to execute the signup process.
 *
// * This controller uses both a general signup interactor and a shared account signup interactor
// * to handle different signup scenarios.
 */
public class SignupController {
    final UserAccountSignupInputBoundary signupInteractor;  // General interactor for standard signup
//    final SharedAccountSignupInteractor sharedAccountSignupInteractor;  // Specific interactor for shared account signup

    /**
     * Constructs a SignupController object with the specified use case interactors.
     *
     * @param signupInteractor             the use case interactor for standard user signup
//     * @param sharedAccountSignupInteractor the use case interactor for shared account signup
     */
    public SignupController(UserAccountSignupInteractor signupInteractor) {
        this.signupInteractor = signupInteractor;
//        this.sharedAccountSignupInteractor = sharedAccountSignupInteractor;
    }

    /**
     * Executes the standard signup process with the given user details.
     *
     * @param username        the username of the user
     * @param password        the password of the user
     * @param identification  the identification of the user
     */
    public void execute(String username, String password, String identification) {
        UserAccountSignupInputData userAccountSignupInputData = new UserAccountSignupInputData(username, password, identification);
        signupInteractor.execute(userAccountSignupInputData);
    }

    //overloading
    /**
     * Executes the shared account signup process with the given user details.
     *
     * @param username        the username of the user
     * @param password        the password of the user
     * @param identification  the identification of the user
//     * @param sharedAccountId the shared account ID
     */
//    public void execute(String username, String password, String identification) {
//        if (sharedAccountId != null && !sharedAccountId.isEmpty()) {
//            SharedAccountSignupInputData signupInputData = new SharedAccountSignupInputData(
//                    username, password, identification, sharedAccountId);
//            sharedAccountSignupInteractor.execute(signupInputData);
//        } else {
//            execute(username, password, identification);  // Calls the method for standard signup
//        }
//    }
}
