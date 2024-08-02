package use_case.signup;

import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.UserAccount;
import entity.account.SharedAccount;
import entity.account.AccountFactory;

/**
 * The SharedAccountSignupInteractor class implements the SignupInputBoundary interface.
 * It handles the signup process for shared accounts by validating the input data,
 * interacting with the data access layer, and using the presenter to prepare the output views.
 * This class extends the functionality of the standard signup to include shared account logic.
 * This class assumes that it will receive a SharedAccountSignupInputData type as input.
 */
public class SharedAccountSignupInteractor implements SignupInputBoundary {
    final AccountFactory accountFactory;
    final SignupOutputBoundary presenter;
    final UserSignupDataAccessInterface userDataAccessObject;

    /**
     * Constructs a SharedAccountSignupInteractor object with the specified data access interface,
     * output boundary, and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for user data
     * @param signupOutputBoundary          the output boundary for presenting the signup results
     * @param accountFactory                the factory for creating user accounts
     */
    public SharedAccountSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                                         SignupOutputBoundary signupOutputBoundary,
                                         AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.presenter = signupOutputBoundary;
    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param signupInputData the input data required for the signup process
     */
    @Override
    public void execute(SignupInputData signupInputData) {
        SharedAccountSignupInputData sharedSignupData = (SharedAccountSignupInputData) signupInputData;

        boolean userExists = userDataAccessObject.existById(sharedSignupData.getIdentification());
        boolean sharedAccountExists = userDataAccessObject.existById(sharedSignupData.getSharedAccountId());

        boolean validUsername = this.checkUsername(sharedSignupData.getUsername());
        boolean validPassword = this.checkPassword(sharedSignupData.getPassword());
        boolean validIdentification = this.checkIdentification(sharedSignupData.getIdentification());
        boolean validSharedAccountId = this.checkIdentification(sharedSignupData.getSharedAccountId());

        if (!validUsername || !validPassword || !validIdentification || !validSharedAccountId) {
            // Simplified the error messages using a single check for all fields
            if (!validUsername) {
                presenter.prepareFailView("Username cannot be empty!");
            }
            if (!validPassword) {
                presenter.prepareFailView("Password cannot be empty!");
            }
            if (!validIdentification) {
                presenter.prepareFailView("Identification cannot be empty!");
            }
            if (!validSharedAccountId) {
                presenter.prepareFailView("Shared Account ID cannot be empty!");
            }
            return;
        }

        if (userExists) {
            UserAccount userAccount = userDataAccessObject.getById(sharedSignupData.getIdentification());

            if (userAccount.getPassword().equals(sharedSignupData.getPassword())) {
                if (sharedAccountExists) {
                    // If both user and shared account exist, handle the user choice in the view
                    SignupOutputData signupOutputData = new SharedAccountSignupOutputData(
                            sharedSignupData.getUsername(), false, sharedSignupData.getSharedAccountId(), true);
                    presenter.prepareSuccessView(signupOutputData);
                } else {
                    // Shared account does not exist, create it
                    SharedAccount newSharedAccount = accountFactory.createSharedAccount(sharedSignupData.getSharedAccountId());
                    userDataAccessObject.save(newSharedAccount);

                    // Prepare success view for shared account creation
                    SignupOutputData signupOutputData = new SharedAccountSignupOutputData(
                            sharedSignupData.getUsername(), false, sharedSignupData.getSharedAccountId(), false);
                    presenter.prepareSuccessView(signupOutputData);
                }
            } else {
                // Password mismatch
                presenter.prepareFailView("Incorrect password for existing user.");
            }
        } else {
            // User does not exist, prepare fail view
            presenter.prepareFailView("Please sign up an individual user account first.");
        }
    }


    /**
     * Checks if the provided username is valid (not null or empty).
     *
     * @param username the username to check
     * @return true if the username is valid, false otherwise
     */
    private boolean checkUsername(String username) {
        return username != null && !username.isEmpty();
    }

    /**
     * Checks if the provided password is valid (not null or empty).
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    private boolean checkPassword(String password) {
        return password != null && !password.isEmpty();
    }

    /**
     * Checks if the provided identification is valid (not null or empty).
     *
     * @param id the identification to check
     * @return true if the identification is valid, false otherwise
     */
    private boolean checkIdentification(String id) {
        return id != null && !id.isEmpty();
    }
}

