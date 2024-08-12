package interface_adaptors.login.user_account;

import interface_adaptors.login.LoginController;
import use_case.login.user_account.UserAccountLoginMediator;
import use_case.login.user_account.UserAccountLoginInputData;

/**
 * The {@code UserAccountLoginController} class is responsible for handling user interactions
 * related to the login process for user accounts. It communicates with the use case interactor (mediator)
 * to execute the login process based on the provided user credentials.
 *
 * <p>This controller is part of the interface adapters layer in a clean architecture, ensuring that
 * user inputs are correctly passed to the application logic without direct dependency on the
 * use case layer.</p>
 *
 * <p><b>Authors:</b> Jessica Chen, Eric Chen</p>
 */
public class UserAccountLoginController extends LoginController<UserAccountLoginMediator> {

    /**
     * Constructs a {@code UserAccountLoginController} object with the specified use case interactor (mediator).
     *
     * @param loginMediator the use case interactor (mediator) for user login
     */
    public UserAccountLoginController(UserAccountLoginMediator loginMediator) {
        super(loginMediator);
    }

    /**
     * Executes the login process with the given user details.
     *
     * @param id       the identification of the user
     * @param password the password of the user
     */
    @Override
    public void execute(String id, String password) {
        UserAccountLoginInputData loginInputData = new UserAccountLoginInputData(id, password);
        this.loginMediator.execute(loginInputData);
    }
}
