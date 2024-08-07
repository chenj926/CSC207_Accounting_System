package interface_adaptors.login;

import use_case.login.*;

public class SharedAccountLoginController{
    final LoginMediator loginMediator;
//    final SharedAccountLoginInputBoundary sharedAccountLoginInteractor;

    public SharedAccountLoginController(LoginMediator loginMediator) {
        this.loginMediator = loginMediator;
    }

    public void execute(String sharedAccountId, String password) {
        SharedAccountLoginInputData sharedAccountLoginInputData = new SharedAccountLoginInputData(sharedAccountId, password);
        this.loginMediator.execute(sharedAccountLoginInputData);
    }
}
