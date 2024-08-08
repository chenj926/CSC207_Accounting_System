package app.authentication;

import data_access.*;
import data_access.account.UserAccountDataAccessInterface;
import data_access.authentication.LoginDataAccessInterface;
import data_access.authentication.SharedAccountLoginDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.*;
import interface_adaptors.login.UserAccountLoginPresenter;
import use_case.login.*;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInteractor;
import view.login.LoginView;
import view.login.SharedAccountLoginView;

import javax.swing.*;
import java.io.IOException;

/**
 * The LoginUseCaseFactory class is responsible for creating instances of LoginView and SharedAccountLoginView.
 * It also creates the necessary components for handling user account login and shared account login use cases.
 * This class ensures the proper initialization of the login controllers and their dependencies.
 * @author Jessica
 * @author Eric
 * @author Zella
 */
public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    /**
     * Creates and returns an instance of LoginView for user account login.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param loginViewModel   the view model to update the login state
     * @return an instance of LoginView
     */
    public static LoginView create(ViewManagerModel viewManagerModel, UserAccountLoginViewModel loginViewModel) {
        try {
            AccountLoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel);
            return new LoginView(loginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and returns an instance of UserAccountLoginController for user account login.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param loginViewModel   the view model to update the login state
     * @return an instance of UserAccountLoginController
     * @throws IOException if an I/O error occurs
     */
    private static UserAccountLoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, UserAccountLoginViewModel loginViewModel) throws IOException {
        LoginDataAccessInterface loginDataAccessObject = DAOFactory.getLoginDataAccessObject();
        UserAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getPeriodicTransactionDAO();

        UserAcountLoginOutputBoundary loginPresenter = new UserAccountLoginPresenter(viewManagerModel, loginViewModel);
        UserAccountLoginInteractor loginInteractor = new UserAccountLoginInteractor(loginDataAccessObject, loginPresenter);

        UpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        LoginMediator loginMediator = new LoginMediator(loginInteractor, updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        loginInteractor.setMediator(loginMediator);
        return new UserAccountLoginController(loginMediator);
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

        UpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        LoginMediator loginMediator = new LoginMediator(interactor, updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        interactor.setMediator(loginMediator);
        return new SharedAccountLoginController(loginMediator);
    }

}

