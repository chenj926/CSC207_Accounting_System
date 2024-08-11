package app.authentication;

import data_access.*;
import data_access.account.user_account.UserAccountDataAccessInterface;
import data_access.authentication.user_account.UserAccountLoginDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.user_account.UserAccountLoginController;
import interface_adaptors.login.user_account.UserAccountLoginPresenter;
import interface_adaptors.login.user_account.UserAccountLoginViewModel;
import use_case.login.user_account.UserAccountLoginInteractor;
import use_case.login.user_account.UserAccountLoginMediator;
import use_case.login.user_account.UserAcountLoginOutputBoundary;
import use_case.update_periodic_at_login.user_account.UserAccountUpdatePeriodicAtLoginInteractor;
import view.login.user_account.UserAccountLoginView;

import javax.swing.*;
import java.io.IOException;

public class UserAccountLoginUseCaseFactory {

    private UserAccountLoginUseCaseFactory() {}

    public static UserAccountLoginView create(ViewManagerModel viewManagerModel, UserAccountLoginViewModel loginViewModel) {
        try {
            UserAccountLoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel);
            return new UserAccountLoginView(loginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static UserAccountLoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, UserAccountLoginViewModel loginViewModel) throws IOException {
        UserAccountLoginDataAccessInterface loginDataAccessObject = DAOFactory.getLoginDataAccessObject();
        UserAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getPeriodicTransactionDAO();

        UserAcountLoginOutputBoundary loginPresenter = new UserAccountLoginPresenter(viewManagerModel, loginViewModel);
        UserAccountLoginInteractor loginInteractor = new UserAccountLoginInteractor(loginDataAccessObject, loginPresenter);

        UserAccountUpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UserAccountUpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        UserAccountLoginMediator userAccountLoginMediator = new UserAccountLoginMediator(loginInteractor, updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        loginInteractor.setMediator(userAccountLoginMediator);
        return new UserAccountLoginController(userAccountLoginMediator);
    }
}

