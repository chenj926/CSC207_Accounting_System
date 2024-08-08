package interface_adaptors.signup;

import use_case.signup.*;

/**
 * The UserAccountSignupController class is responsible for handling user interactions related to the signup process.
 * It communicates with the use case interactor to execute the signup process.
 *
// * This controller uses both a general signup interactor and a shared account signup interactor
// * to handle different signup scenarios.
 */
public class UserAccountSignupController extends AccountSignupController<
        UserAccountSignupInputBoundary,
        UserAccountSignupInteractor>{

    /**
     * Constructs a UserAccountSignupController object with the specified use case interactors.
     *
     * @param signupInteractor             the use case interactor for standard user signup
     * @param signupInteractor the use case interactor for shared account signup
     */
    public UserAccountSignupController(UserAccountSignupInteractor signupInteractor) {
        super(signupInteractor);
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

}
