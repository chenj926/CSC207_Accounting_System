package app.authentication;

import data_access.DAOFactory;
import data_access.authentication.UserSignupDataAccessInterface;
import data_access.account.ShareAccountDataAccessInterface;
import entity.account.AccountFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SharedAccountSignupController;
import interface_adaptors.signup.UserAccountSignupPresenter;
import interface_adaptors.signup.SharedAccountSignupPresenter;
import interface_adaptors.signup.SignupViewModel;
import interface_adaptors.signup.SharedAccountSignupViewModel;
import use_case.signup.*;
import view.signup.SignupView;
import view.signup.SharedAccountSignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel) {
        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel);
            return new SignupView(signupViewModel, signupController, viewManagerModel);
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

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel) throws IOException {
        UserSignupDataAccessInterface dataAccessObject = DAOFactory.getUserSignupDataAccessObject();
        UserAccountSignupOutputBoundary presenter = new UserAccountSignupPresenter(viewManagerModel, signupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        // Create the general signup interactor
        UserAccountSignupInteractor signupInteractor = new UserAccountSignupInteractor(dataAccessObject, presenter, accountFactory);

        // Return the controller for standard signup
        return new SignupController(signupInteractor); // Pass null for Interactor
    }

    private static SharedAccountSignupController createSharedAccountSignupUseCase(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedSignupViewModel) throws IOException {
        UserSignupDataAccessInterface dataAccessObject = DAOFactory.getUserSignupDataAccessObject();
        ShareAccountDataAccessInterface sharedDataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountSignupOutputBoundary presenter = new SharedAccountSignupPresenter(viewManagerModel, sharedSignupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        SharedAccountSignupInteractor signupInteractor = new SharedAccountSignupInteractor(dataAccessObject, sharedDataAccessObject, presenter, accountFactory);
        return new SharedAccountSignupController(signupInteractor); // Pass null for sharedInteractor
    }
}



