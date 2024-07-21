package use_case;

import entity.UserAccount;
import entity.AccountFactory;
import data_access.LoginDataAccessInterface;


public class LoginInteractor implements LoginInputBoundary {
//    final AccountFactory accountFactory;
    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary presenter;

    public LoginInteractor(LoginDataAccessInterface LoginDataAccessInterface, LoginOutputBoundary logInOutputBoundary) {
//        this.accountFactory = accountFactory;
        this.userDataAccessObject = LoginDataAccessInterface;
        this.presenter = logInOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {

            // check if username or password or id is valid (not empty)
            boolean validPassword = this.checkPassword(loginInputData.getPassword());
            boolean validIdentificaiton = this.checkIdentification(loginInputData.getIdentification());

            if (!validPassword && !validIdentificaiton) {
                presenter.prepareFailView("Identification AND Password can not be empty!");
                return;
            } else if (!validPassword) {
                presenter.prepareFailView("Password can not be empty!");
                return;
            } else if (!validIdentificaiton) {
                presenter.prepareFailView("Identification can not be empty!");
                return;
            } else {
                if (!userDataAccessObject.existById(loginInputData.getIdentification())) {
                    //User does not exist
                    presenter.prepareFailView("User not found");
                    return;
                }

                //User exists, fetch the user account
                UserAccount userAccount = userDataAccessObject.getById(loginInputData.getIdentification());
                if (userAccount != null && !userAccount.getPassword().equals(loginInputData.getPassword())) {
                    presenter.prepareFailView("Incorrect Password!");
                    return;
                }

                // attempt to login
                boolean isLogin = userDataAccessObject.login(userAccount);

                // if login failed, password wrong
                if (!isLogin) {
                    presenter.prepareFailView("Incorrect Password!");
                } else {
                    // prepare output to presenter
                    LoginOutputData loginOutputData = new LoginOutputData(userAccount.getIdentification(), true);
                    presenter.prepareSuccessView(loginOutputData);
                    }
                }

        }


    public boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkIdentification(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return true;
    }

}
