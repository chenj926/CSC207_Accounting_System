package interface_adaptors.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.login.LoginMediator;

/**
 * The LoginController class is responsible for handling user interactions related to the login process.
 * It communicates with the use case interactor to execute the login process.
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class LoginController {
    final LoginMediator loginMediator;

    /**
     * Constructs a LoginController object with the specified use case interactor(mediator).
     *
     * @param loginMediator the use case interactor(mediator) for user login
     */
    public LoginController(LoginMediator loginMediator) {
        this.loginMediator = loginMediator;
    }

    /**
     * Executes the login process with the given user details.
     *
     * @param password the password of the user
     * @param id       the identification of the user
     */
    public void execute(String password, String id) {
        LoginInputData loginInputDate = new LoginInputData(password, id);
        loginMediator.execute(loginInputDate);
    }
}
