package app.authentication;

import data_access.*;
import data_access.account.UserAccountDataAccessInterface;
import data_access.authentication.LoginDataAccessInterface;
import data_access.account.ShareAccountDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.LoginController;
import interface_adaptors.login.LoginPresenter;
import interface_adaptors.login.LoginViewModel;
import interface_adaptors.login.SharedAccountLoginController;
import interface_adaptors.login.SharedAccountLoginPresenter;
import interface_adaptors.login.SharedAccountLoginViewModel;
import use_case.login.LoginInputBoundary;
import interface_adaptors.transaction.periodic.PeriodicTransactionPresenter;
import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;
import use_case.login.LoginInteractor;
import use_case.login.LoginMediator;
import use_case.login.LoginOutputBoundary;
import use_case.login.SharedAccountLoginInputBoundary;
import use_case.login.SharedAccountLoginInteractor;
import use_case.login.SharedAccountLoginOutputBoundary;
import use_case.transaction.periodic.PeriodicTransactionInteractor;
import use_case.transaction.periodic.PeriodicTransactionOutputBoundary;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInteractor;
import view.login.LoginView;
import view.login.SharedAccountLoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel);
            return new LoginView(loginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) throws IOException {
        LoginDataAccessInterface loginDataAccessObject = DAOFactory.getLoginDataAccessObject();
        UserAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getPeriodicTransactionDAO();

        LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(loginDataAccessObject, loginPresenter);

        UpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        LoginMediator loginMediator = new LoginMediator(loginInteractor, updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        loginInteractor.setMediator(loginMediator);
        return new LoginController(loginMediator);
    }
}

