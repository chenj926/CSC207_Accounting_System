package interface_adaptors.login;

import use_case.login.SharedAccountLoginInputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The LoginController class is responsible for handling user interactions related to the login process.
 * It communicates with the use case interactor to execute the login process.
 *
 * This controller uses both a general login interactor and a shared account login interactor
 * to handle different login scenarios.
 */
public class LoginController {
    final LoginInputBoundary loginInteractor; // General interactor for standard login
//    final LoginInputBoundary sharedAccountLoginInteractor; // Interactor for shared account login

    /**
     * Constructs a LoginController object with the specified use case interactors.
     *
     * @param loginInteractor the use case interactor for standard user login
//     * @param sharedAccountLoginInteractor the use case interactor for shared account user login
     */
    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
//        this.sharedAccountLoginInteractor = sharedAccountLoginInteractor;
    }

    /**
     * Executes the standard login process with the given user details.
     *
     * @param password the password of the user
     * @param id       the identification of the user
     */
    public void execute(String password, String id) {
        LoginInputData loginInputData = new LoginInputData(password, id);
        loginInteractor.execute(loginInputData);
    }

//    /**
//     * Executes the shared account login process with the given user details.
//     *
//     * @param password the password of the user
//     * @param id       the identification of the user
//     * @param sharedAccountId the shared account ID
//     */
//    public void execute(String password, String id, String sharedAccountId) {
//        if (sharedAccountId != null && !sharedAccountId.isEmpty()) {
//            SharedAccountLoginInputData loginInputData = new SharedAccountLoginInputData(password, id, sharedAccountId);
//            sharedAccountLoginInteractor.execute(loginInputData);
//        } else {
//            execute(password, id); // Calls the method for standard login
//        }
//    }
}


