package use_case;

import java.util.*;

import data_access.UserSignupDataAccessInterface;
import entity.UserAccount;
import entity.AccountFactory;

public class SignupInteractor implements SignupInputBoundary{
    final AccountFactory accountFactory;
    final SignupOutputBoundary presenter;
    final UserSignupDataAccessInterface userDataAccessObject;

    // Constructor
    public SignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.presenter = signupOutputBoundary;

    }

    @Override
    public void excute(SignupInputData signupInputData) {
        if (userDataAccessObject.existById(signupInputData.getIdentification())) {
            // user already exist, return back fail view to presenter
            presenter.prepareFailView("User already exist!!!");
        }
        else {
            // create new user
            UserAccount newUser = accountFactory.createUserAccount(signupInputData.getUsername(),
                    signupInputData.getPassword(), signupInputData.getIdentification());
            userDataAccessObject.save(newUser);  // save this user

            // prepare output to presenter
            SignupOutputData signupOutputData = new SignupOutputData(newUser.getUsername(), false);
            presenter.prepareSuccessView(signupOutputData);
        }
    }
}