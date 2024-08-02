package app.authentication;

import data_access.DAOFactory;
import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.AccountFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupPresenter;
import interface_adaptors.signup.SignupViewModel;
import use_case.signup.SharedAccountSignupInteractor;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
//import use_case.signup.StandardSignupInteractor;
import view.signup.SignupPanel;
import view.signup.SignupView;

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

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel) throws IOException {
        UserSignupDataAccessInterface dataAccessObject = DAOFactory.getUserSignupDataAccessObject();
        SignupOutputBoundary presenter = new SignupPresenter(viewManagerModel, signupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        // Create the interactors for standard and shared account signups
        SignupInteractor signupInteractor = new SignupInteractor(dataAccessObject, presenter, accountFactory);
        SharedAccountSignupInteractor sharedInteractor = new SharedAccountSignupInteractor(dataAccessObject, presenter, accountFactory);

        // Return the controller that can handle both types of signups
        return new SignupController(signupInteractor, sharedInteractor);
    }
}


