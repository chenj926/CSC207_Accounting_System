package app;

import data_access.*;
import entity.*;
import interface_adaptors.*;
import use_case.*;
import view.*;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) throws IOException {
        LoginDataAccessInterface dataAccessObject = (LoginDataAccessInterface) DAOFactory.getUserSignupDataAccessObject();
        AccountFactory accountFactory = new AccountFactory();
        LoginOutputBoundary presenter = new LoginPresenter(viewManagerModel, loginViewModel);
        LoginInteractor interactor = new LoginInteractor(dataAccessObject, presenter, accountFactory);
        return new LoginController(interactor);
    }
}
