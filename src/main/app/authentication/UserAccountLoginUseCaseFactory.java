package app.authentication;

import data_access.*;
import data_access.account.UserAccountDataAccessInterface;
import data_access.authentication.LoginDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.*;
import interface_adaptors.login.UserAccountLoginPresenter;
import use_case.login.*;
import use_case.update_periodic_at_login.UserAccountUpdatePeriodicAtLoginInteractor;
import view.login.LoginView;

import javax.swing.*;
import java.io.IOException;

public class UserAccountLoginUseCaseFactory {

    private UserAccountLoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, UserAccountLoginViewModel loginViewModel) {
        try {
            UserAccountLoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel);
            return new LoginView(loginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static UserAccountLoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, UserAccountLoginViewModel loginViewModel) throws IOException {
        LoginDataAccessInterface loginDataAccessObject = DAOFactory.getLoginDataAccessObject();
        UserAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getPeriodicTransactionDAO();

        UserAcountLoginOutputBoundary loginPresenter = new UserAccountLoginPresenter(viewManagerModel, loginViewModel);
        UserAccountLoginInteractor loginInteractor = new UserAccountLoginInteractor(loginDataAccessObject, loginPresenter);

        UserAccountUpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UserAccountUpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        UserAccountLoginMediator userAccountLoginMediator = new UserAccountLoginMediator(loginInteractor, updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        loginInteractor.setMediator(userAccountLoginMediator);
        return new UserAccountLoginController(userAccountLoginMediator);
    }
}

