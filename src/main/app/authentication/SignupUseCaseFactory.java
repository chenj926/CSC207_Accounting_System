package app.authentication;

import data_access.DAOFactory;
import data_access.authentication.UserSignupDataAccessInterface;
import data_access.account.ShareAccountDataAccessInterface;
import entity.account.AccountFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupPresenter;
import interface_adaptors.signup.SharedAccountSignupPresenter;
import interface_adaptors.signup.SignupViewModel;
import interface_adaptors.signup.SharedAccountSignupViewModel;
import use_case.signup.SharedAccountSignupInteractor;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
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
            SignupController signupController = createSharedAccountSignupUseCase(viewManagerModel, sharedSignupViewModel);
            return new SharedAccountSignupView(sharedSignupViewModel, signupController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel) throws IOException {
        UserSignupDataAccessInterface dataAccessObject = DAOFactory.getUserSignupDataAccessObject();
        SignupOutputBoundary presenter = new SignupPresenter(viewManagerModel, signupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        // Create the general signup interactor
        SignupInteractor signupInteractor = new SignupInteractor(dataAccessObject, presenter, accountFactory);

        // Return the controller for standard signup
        return new SignupController(signupInteractor, null); // Pass null for sharedInteractor
    }

    private static SignupController createSharedAccountSignupUseCase(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedSignupViewModel) throws IOException {
        UserSignupDataAccessInterface dataAccessObject = DAOFactory.getUserSignupDataAccessObject();
        ShareAccountDataAccessInterface sharedDataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SignupOutputBoundary sharedPresenter = new SharedAccountSignupPresenter(viewManagerModel, sharedSignupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        // Create the shared account signup interactor
        SharedAccountSignupInteractor sharedInteractor = new SharedAccountSignupInteractor(dataAccessObject, sharedDataAccessObject, sharedPresenter, accountFactory);

        // Return the controller for shared account signup
        return new SignupController(null, sharedInteractor); // Pass null for standardInteractor
    }
}



