package app.authentication;

import data_access.*;
import data_access.account.UserAccountDataAccessInterface;
import data_access.authentication.LoginDataAccessInterface;
import data_access.authentication.SharedAccountLoginDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.*;
import interface_adaptors.login.UserAccountLoginPresenter;
import use_case.login.*;
import use_case.update_periodic_at_login.UserAccountUpdatePeriodicAtLoginInteractor;
import view.login.LoginView;
import view.login.SharedAccountLoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, UserAccountLoginViewModel loginViewModel) {
        try {
            AccountLoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel);
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

    public static SharedAccountLoginView create(ViewManagerModel viewManagerModel,
                                                SharedAccountLoginViewModel viewModel) {
        try {
            SharedAccountLoginController controller = createSharedAccountUserLoginUseCase(viewManagerModel, viewModel);
            return new SharedAccountLoginView(viewModel, controller, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SharedAccountLoginController createSharedAccountUserLoginUseCase(ViewManagerModel viewManagerModel,
                                                                       SharedAccountLoginViewModel viewModel) throws IOException {
        SharedAccountLoginDataAccessInterface loginDataAccessObject = DAOFactory.getSharedAccountLoginDataAccessObject();
        UserAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getPeriodicTransactionDAO();

        SharedAccountLoginOutputBoundary presenter = new SharedAccountLoginPresenter(viewManagerModel, viewModel);
        SharedAccountLoginInteractor interactor = new SharedAccountLoginInteractor(loginDataAccessObject, presenter);

        UserAccountUpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UserAccountUpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        UserAccountLoginMediator userAccountLoginMediator = new UserAccountLoginMediator(interactor, updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        interactor.setMediator(userAccountLoginMediator);
        return new SharedAccountLoginController(userAccountLoginMediator);
    }

}

