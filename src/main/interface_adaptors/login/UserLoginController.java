package interface_adaptors.login;

import use_case.login.LoginMediator;
import use_case.login.UserLoginInputData;

/**
 * The LoginController class is responsible for handling user interactions related to the login process.
 * It communicates with the use case interactor to execute the login process.
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class UserLoginController {
    final LoginMediator loginMediator;

    /**
     * Constructs a LoginController object with the specified use case interactor(mediator).
     *
     * @param loginMediator the use case interactor(mediator) for user login
     */
    public UserLoginController(LoginMediator loginMediator) {
        this.loginMediator = loginMediator;
    }

    /**
     * Executes the login process with the given user details.
     *
     * @param password the password of the user
     * @param id       the identification of the user
     */
    public void execute(String password, String id) {
        UserLoginInputData loginInputData = new UserLoginInputData(password, id);
        this.loginMediator.execute(loginInputData);
    }
}
