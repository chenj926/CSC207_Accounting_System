package interface_adaptors.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The LoginController class is responsible for handling user interactions related to the login process.
 * It communicates with the use case interactor to execute the login process.
 *
 * @author Eric
 * @author Dana
 */
public class LoginController {
    final LoginInputBoundary loginInputInteractor;

    /**
     * Constructs a LoginController object with the specified use case interactor.
     *
     * @param loginInputInteractor the use case interactor for user login
     */
    public LoginController(LoginInputBoundary loginInputInteractor) {
        this.loginInputInteractor = loginInputInteractor;
    }

    /**
     * Executes the login process with the given user details.
     *
     * @param password the password of the user
     * @param id       the identification of the user
     */
    public void execute(String password, String id) {
        LoginInputData loginInputDate = new LoginInputData(password, id);
        loginInputInteractor.execute(loginInputDate);
    }
}
