package interface_adaptors.login;

import use_case.login.*;
import use_case.login.LoginInteractor;

public class SharedAccountLoginController extends LoginController {
    final SharedAccountLoginInputBoundary sharedAccountLoginInteractor;

    public SharedAccountLoginController(LoginInteractor loginInteractor,
                                        SharedAccountLoginInputBoundary sharedAccountLoginInteractor) {
        super(loginInteractor);
        this.sharedAccountLoginInteractor = sharedAccountLoginInteractor;
    }

    public void execute(String password, String id, String sharedAccountId) {
        SharedAccountLoginInputData sharedAccountLoginInputData = new SharedAccountLoginInputData(password, id,
                sharedAccountId);

        sharedAccountLoginInteractor.execute(sharedAccountLoginInputData);
    }
}
