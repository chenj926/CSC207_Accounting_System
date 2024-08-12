package app.authentication;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.authentication.shared_account.SharedAccountSignupDataAccessInterface;
import data_access.authentication.user_account.UserSignupDataAccessInterface;
import entity.account.AccountFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.shared_account.SharedAccountSignupController;
import interface_adaptors.signup.shared_account.SharedAccountSignupPresenter;
import interface_adaptors.signup.shared_account.SharedAccountSignupViewModel;
import interface_adaptors.signup.user_account.UserAccountSignupController;
import interface_adaptors.signup.user_account.UserAccountSignupPresenter;
import interface_adaptors.signup.user_account.UserAccountSignupViewModel;
import use_case.signup.shared_account.SharedAccountSignupInteractor;
import use_case.signup.shared_account.SharedAccountSignupOutputBoundary;
import use_case.signup.user_account.UserAccountSignupInteractor;
import use_case.signup.user_account.UserAccountSignupOutputBoundary;
import view.signup.user_account.UserAccountSignupView;
import view.signup.shared_account.SharedAccountSignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    private SignupUseCaseFactory() {}

    public static UserAccountSignupView create(ViewManagerModel viewManagerModel, UserAccountSignupViewModel userAccountSignupViewModel) {
        try {
            UserAccountSignupController userAccountSignupController = createUserSignupUseCase(viewManagerModel, userAccountSignupViewModel);
            return new UserAccountSignupView(userAccountSignupViewModel, userAccountSignupController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public static SharedAccountSignupView createSharedAccount(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedAccountSignupViewModel) {
        try {
            SharedAccountSignupController sharedAccountSignupController = createSharedAccountSignupUseCase(viewManagerModel, sharedAccountSignupViewModel);
            return new SharedAccountSignupView(sharedAccountSignupViewModel, sharedAccountSignupController, viewManagerModel);
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

    private static SharedAccountSignupController createSharedAccountSignupUseCase(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedAccountSignupViewModel) throws IOException {
        SharedAccountSignupDataAccessInterface dataAccessObject = DAOFactory.getSharedAccountSignupDataAccessObject();
        SharedAccountDataAccessInterface sharedDataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountSignupOutputBoundary presenter = new SharedAccountSignupPresenter(viewManagerModel, sharedAccountSignupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        SharedAccountSignupInteractor signupInteractor = new SharedAccountSignupInteractor(dataAccessObject, sharedDataAccessObject, presenter, accountFactory);
        return new SharedAccountSignupController(signupInteractor); // Pass null for sharedInteractor
    }
}



