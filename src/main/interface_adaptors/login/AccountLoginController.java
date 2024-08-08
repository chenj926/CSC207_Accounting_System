package interface_adaptors.login;

import use_case.login.LoginMediator;

public abstract class AccountLoginController {
    protected final LoginMediator loginMediator;

    /**
     * Constructs a LoginController object with the specified use case interactor(mediator).
     *
     * @param loginMediator the use case interactor(mediator) for user login
     */
    public AccountLoginController(LoginMediator loginMediator) {
        this.loginMediator = loginMediator;
    }

    public void execute (String identification, String password) {}
}
