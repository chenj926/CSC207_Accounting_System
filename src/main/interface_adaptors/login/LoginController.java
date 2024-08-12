package interface_adaptors.login;

import use_case.login.LoginMediator;

public abstract class LoginController<LM extends LoginMediator> {
    protected final LM loginMediator;

    /**
     * Constructs a LoginController object with the specified use case interactor(mediator).
     *
     * @param userAccountLoginMediator the use case interactor(mediator) for user login
     */
    public LoginController(LM userAccountLoginMediator) {
        this.loginMediator = userAccountLoginMediator;
    }

    public void execute (String identification, String password) {}
}
