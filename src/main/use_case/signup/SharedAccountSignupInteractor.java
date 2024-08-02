package use_case.signup;

import data_access.account.ShareAccountDataAccessInterface;
import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.UserAccount;
import entity.account.SharedAccount;
import entity.account.AccountFactory;

/**
 * The SharedAccountSignupInteractor class extends SignupInteractor to add shared account signup logic.
 * It handles the signup process for shared accounts by validating the input data,
 * interacting with the data access layer, and using the presenter to prepare the output views.
 * This class assumes that it will receive a SharedAccountSignupInputData type as input.
 */
public class SharedAccountSignupInteractor extends SignupInteractor {

    private final ShareAccountDataAccessInterface sharedDataAccessObject; // Interface for shared accounts

    /**
     * Constructs a SharedAccountSignupInteractor object with the specified data access interface,
     * output boundary, and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for user data
     * @param sharedDataAccessObject        the data access interface for shared accounts
     * @param signupOutputBoundary          the output boundary for presenting the signup results
     * @param accountFactory                the factory for creating user accounts
     */
    public SharedAccountSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                                         ShareAccountDataAccessInterface sharedDataAccessObject, // Added sharedDataAccessObject
                                         SignupOutputBoundary signupOutputBoundary,
                                         AccountFactory accountFactory) {
        super(userSignupDataAccessInterface, signupOutputBoundary, accountFactory);
        this.sharedDataAccessObject = sharedDataAccessObject; // Initialize sharedDataAccessObject
    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param signupInputData the input data required for the signup process
     */
    @Override
    public void execute(SignupInputData signupInputData) {
        if (!(signupInputData instanceof SharedAccountSignupInputData)) {
            throw new IllegalArgumentException("Invalid input data type for SharedAccountSignupInteractor");
        }

        SharedAccountSignupInputData sharedSignupData = (SharedAccountSignupInputData) signupInputData;

        // Use userDataAccessObject for user accounts
        boolean userExists = userDataAccessObject.existById(sharedSignupData.getIdentification());

        // Use sharedDataAccessObject for shared accounts
        boolean sharedAccountExists = sharedDataAccessObject.existById(sharedSignupData.getSharedAccountId());

        if (userExists) {
            // Ensure user password matches before proceeding
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
                    newSharedAccount.addUserIdentification(sharedSignupData.getIdentification()); // Add user to shared account
                    sharedDataAccessObject.save(newSharedAccount); // Use sharedDataAccessObject to save shared account

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
}

