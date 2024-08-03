package app.authentication;

import data_access.DAOFactory;
import data_access.authentication.LoginDataAccessInterface;
import data_access.account.ShareAccountDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.LoginController;
import interface_adaptors.login.LoginPresenter;
import interface_adaptors.login.LoginViewModel;
import interface_adaptors.login.SharedAccountLoginPresenter;
import interface_adaptors.login.SharedAccountLoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.SharedAccountLoginInteractor;
import view.login.LoginView;
import view.login.SharedAccountLoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    /**
     * Creates a LoginView for standard user login.
     *
     * @param viewManagerModel the view manager model
     * @param loginViewModel   the login view model
     * @return the login view
     */
    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        try {
            LoginController loginController = createStandardLoginUseCase(viewManagerModel, loginViewModel);
            return new LoginView(loginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates a SharedAccountLoginView for shared account login.
     *
     * @param viewManagerModel the view manager model
     * @param sharedAccountLoginViewModel the shared account login view model
     * @return the shared account login view
     */
    public static SharedAccountLoginView createSharedAccount(ViewManagerModel viewManagerModel, SharedAccountLoginViewModel sharedAccountLoginViewModel) {
        try {
            LoginController loginController = createSharedAccountLoginUseCase(viewManagerModel, sharedAccountLoginViewModel);
            return new SharedAccountLoginView(sharedAccountLoginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open shared account data file.");
        }
        return null;
    }

    /**
     * Creates the standard user login use case by setting up the interactor, presenter, and controller.
     *
     * @param viewManagerModel the view manager model
     * @param loginViewModel   the login view model
     * @return the login controller
     * @throws IOException if there is an error accessing data files
     */
    private static LoginController createStandardLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) throws IOException {
        // Obtain the data access objects
        LoginDataAccessInterface loginDataAccessObject = DAOFactory.getLoginDataAccessObject();

        // Create the presenter
        LoginOutputBoundary presenter = new LoginPresenter(viewManagerModel, loginViewModel);

        // Create the login interactor
        LoginInputBoundary standardLoginInteractor = new LoginInteractor(loginDataAccessObject, presenter);

        // Return the controller capable of handling standard login
        return new LoginController(standardLoginInteractor, null);
    }

    /**
     * Creates the shared account login use case by setting up the interactor, presenter, and controller.
     *
     * @param viewManagerModel the view manager model
     * @param sharedAccountLoginViewModel the shared account login view model
     * @return the login controller
     * @throws IOException if there is an error accessing data files
     */
    private static LoginController createSharedAccountLoginUseCase(ViewManagerModel viewManagerModel, SharedAccountLoginViewModel sharedAccountLoginViewModel) throws IOException {
        // Obtain the data access objects
        LoginDataAccessInterface loginDataAccessObject = DAOFactory.getLoginDataAccessObject();
        ShareAccountDataAccessInterface sharedDataAccessObject = DAOFactory.getShareAccountDataAccessObject();

        // Create the shared account presenter
        LoginOutputBoundary sharedPresenter = new SharedAccountLoginPresenter(viewManagerModel, sharedAccountLoginViewModel);

        // Create the shared account login interactor
        LoginInputBoundary sharedLoginInteractor = new SharedAccountLoginInteractor(loginDataAccessObject, sharedDataAccessObject, sharedPresenter);

        // Return the controller capable of handling shared account login
        return new LoginController(null, sharedLoginInteractor);
    }
}



