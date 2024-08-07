package interface_adaptors.login;

import use_case.login.*;
import use_case.login.LoginInteractor;

public class SharedAccountLoginController{
    final SharedAccountLoginInputBoundary sharedAccountLoginInteractor;

    public SharedAccountLoginController(SharedAccountLoginInputBoundary sharedAccountLoginInteractor) {
        this.sharedAccountLoginInteractor = sharedAccountLoginInteractor;
    }

    public void execute(String sharedAccountId, String password) {
        SharedAccountLoginInputData sharedAccountLoginInputData = new SharedAccountLoginInputData(sharedAccountId, password);
        sharedAccountLoginInteractor.execute(sharedAccountLoginInputData);
    }
}
