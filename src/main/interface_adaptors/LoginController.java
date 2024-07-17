package interface_adaptors;

import use_case.LoginInputBoundary;
import use_case.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    public void excute(String username, String password, String id) {
        LoginInputData loginInputDate = new LoginInputData(username, password, id);
        loginInputBoundary.execute(loginInputDate);
    }
}
