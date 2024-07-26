package app;

import data_access.*;
import entity.*;
import interface_adaptors.*;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupPresenter;
import interface_adaptors.signup.SignupViewModel;
import use_case.Transaction.SignupInteractor;
import use_case.Transaction.SignupOutputBoundary;
import view.Signup.SignupView;

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
        SignupInteractor interactor = new SignupInteractor(dataAccessObject, presenter, accountFactory);

        return new SignupController(interactor);
    }
}
