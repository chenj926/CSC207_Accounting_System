package interface_adaptors.login;

import use_case.login.UserAccountLoginMediator;
import use_case.login.UserAccountLoginInputData;

/**
 * The LoginController class is responsible for handling user interactions related to the login process.
 * It communicates with the use case interactor to execute the login process.
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class UserAccountLoginController extends AccountLoginController<UserAccountLoginMediator> {

    /**
     * Constructs a LoginController object with the specified use case interactor(mediator).
     *
     * @param loginMediator the use case interactor(mediator) for user login
     */
    public UserAccountLoginController(UserAccountLoginMediator loginMediator) {
        super(loginMediator);
    }

    /**
     * Executes the login process with the given user details.
     *
     * @param id       the identification of the user
     *@param password the password of the user
     */
    @Override
    public void execute(String id, String password) {
        UserAccountLoginInputData loginInputData = new UserAccountLoginInputData(id, password);
        this.loginMediator.execute(loginInputData);
    }
}
