package interface_adaptors;

import use_case.LoginInputBoundary;
import use_case.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    public void execute(String password, String id) {
        LoginInputData loginInputDate = new LoginInputData(password, id);
        loginInputBoundary.execute(loginInputDate);
    }
}
