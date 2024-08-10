package app.authentication;

import data_access.DAOFactory;
import data_access.account.SharedAccountDataAccessInterface;
import data_access.authentication.SharedAccountSignupDataAccessInterface;
import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.AccountFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.*;
import interface_adaptors.signup.UserAccountSignupViewModel;
import use_case.signup.*;
import view.signup.SignupView;
import view.signup.SharedAccountSignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, UserAccountSignupViewModel userAccountSignupViewModel) {
        try {
            UserAccountSignupController userAccountSignupController = createUserSignupUseCase(viewManagerModel, userAccountSignupViewModel);
            return new SignupView(userAccountSignupViewModel, userAccountSignupController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public static SharedAccountSignupView createSharedAccount(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedSignupViewModel) {
        try {
            SharedAccountSignupController sharedSignupController = createSharedAccountSignupUseCase(viewManagerModel, sharedSignupViewModel);
            return new SharedAccountSignupView(sharedSignupViewModel, sharedSignupController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static UserAccountSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, UserAccountSignupViewModel userAccountSignupViewModel) throws IOException {
        UserSignupDataAccessInterface dataAccessObject = DAOFactory.getUserSignupDataAccessObject();
        UserAccountSignupOutputBoundary presenter = new UserAccountSignupPresenter(viewManagerModel, userAccountSignupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        // Create the general signup interactor
        UserAccountSignupInteractor signupInteractor = new UserAccountSignupInteractor(dataAccessObject, presenter, accountFactory);

        // Return the controller for standard signup
        return new UserAccountSignupController(signupInteractor); // Pass null for Interactor
    }

    private static SharedAccountSignupController createSharedAccountSignupUseCase(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedSignupViewModel) throws IOException {
        SharedAccountSignupDataAccessInterface dataAccessObject = DAOFactory.getSharedAccountSignupDataAccessObject();
        SharedAccountDataAccessInterface sharedDataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountSignupOutputBoundary presenter = new SharedAccountSignupPresenter(viewManagerModel, sharedSignupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        SharedAccountSignupInteractor signupInteractor = new SharedAccountSignupInteractor(dataAccessObject, sharedDataAccessObject, presenter, accountFactory);
        return new SharedAccountSignupController(signupInteractor); // Pass null for sharedInteractor
    }
}



