package interface_adaptors;

import use_case.LoginInputBoundary;
import use_case.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginInputInteractor;

    public LoginController(LoginInputBoundary loginInputInteractor) {
        this.loginInputInteractor = loginInputInteractor;
    }

    public void execute(String password, String id) {
        LoginInputData loginInputDate = new LoginInputData(password, id);
        loginInputInteractor.execute(loginInputDate);
    }
}
