package interface_adaptors.login;

import use_case.login.UserAccountLoginMediator;

public abstract class AccountLoginController {
    protected final UserAccountLoginMediator userAccountLoginMediator;

    /**
     * Constructs a LoginController object with the specified use case interactor(mediator).
     *
     * @param userAccountLoginMediator the use case interactor(mediator) for user login
     */
    public AccountLoginController(UserAccountLoginMediator userAccountLoginMediator) {
        this.userAccountLoginMediator = userAccountLoginMediator;
    }

    public void execute (String identification, String password) {}
}
