package app;

import data_access.*;
import interface_adaptors.*;
import use_case.*;
import view.Login.LoginView;

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
        LoginDataAccessInterface dataAccessObject = DAOFactory.getLoginDataAccessObject();
//        AccountFactory accountFactory = new AccountFactory();
        LoginOutputBoundary presenter = new LoginPresenter(viewManagerModel, loginViewModel);
        LoginInteractor interactor = new LoginInteractor(dataAccessObject, presenter);
        return new LoginController(interactor);
    }
}
