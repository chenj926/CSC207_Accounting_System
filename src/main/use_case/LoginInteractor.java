package use_case;

import entity.UserAccount;
import entity.AccountFactory;
import data_access.LoginDataAccessInterface;


public class LoginInteractor implements LoginInputBoundary {
    final AccountFactory accountFactory;
    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary presenter;

    public LoginInteractor(LoginDataAccessInterface LoginDataAccessInterface, LoginOutputBoundary logInOutputBoundary,
                           AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        this.userDataAccessObject = LoginDataAccessInterface;
        this.presenter = logInOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        if (!userDataAccessObject.existById(loginInputData.getIdentification())) {
            //User does not exist
            presenter.prepareFailView("User not found");
            return;
        }

        else {
            //User exists, fetch the user account
            UserAccount userAccount = userDataAccessObject.getById(loginInputData.getIdentification());
            userDataAccessObject.login(userAccount);

            // prepare output to presenter
            LogInOutputData loginOutputData = new LogInOutputData(userAccount.getUsername(), true);
            presenter.prepareSuccessView(loginOutputData);

        }
    }
}
