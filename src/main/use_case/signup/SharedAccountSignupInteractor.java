package use_case.signup;

import data_access.account.ShareAccountDataAccessInterface;
import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.SharedAccount;
import entity.account.AccountFactory;
import entity.account.UserAccount;
import java.util.Set;

/**
 * The SharedAccountSignupInteractor class handles the signup process for shared accounts.
 * It validates input data, interacts with the data access layer, and uses the presenter to prepare output views.
 * This class does not extend SignupInteractor but follows a similar process.
 * <p>
 * This class assumes that it will receive a SharedAccountSignupInputData type as input.
 * </p>
 *
 * @author Dana
 */
public class SharedAccountSignupInteractor implements SharedAccountSignupInputBoundary {

    final AccountFactory accountFactory;
    final SignupOutputBoundary presenter;
    final UserSignupDataAccessInterface userDataAccessObject;
    final ShareAccountDataAccessInterface sharedDataAccessObject; // Interface for shared accounts

    /**
     * Constructs a SharedAccountSignupInteractor object with the specified data access interfaces,
     * output boundary, and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for user data
     * @param sharedDataAccessObject        the data access interface for shared accounts
     * @param signupOutputBoundary          the output boundary for presenting the signup results
     * @param accountFactory                the factory for creating user accounts
     */
    public SharedAccountSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                                         ShareAccountDataAccessInterface sharedDataAccessObject,
                                         SignupOutputBoundary signupOutputBoundary,
                                         AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.sharedDataAccessObject = sharedDataAccessObject;
        this.presenter = signupOutputBoundary;
    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param sharedSignupData the input data required for the signup process
     */
    public void execute(SharedAccountSignupInputData sharedSignupData) {

        // Validate input fields
        boolean validSharedAccountId = checkIdentification(sharedSignupData.getSharedAccountId());
        boolean validSharedPassword = checkPassword(sharedSignupData.getSharedPassword());
        boolean validUser1Id = checkIdentification(sharedSignupData.getUser1Id());
        boolean validUser2Id = checkIdentification(sharedSignupData.getUser2Id());

        if (!validSharedAccountId || !validSharedPassword || !validUser1Id || !validUser2Id) {
            presenter.prepareFailView("All fields must be filled out!");
            return;
        }

        // Check if the shared account already exists
        if (sharedDataAccessObject.existById(sharedSignupData.getSharedAccountId())) {
            presenter.prepareFailView("A shared account with this ID already exists.");
            return;
        }

        // Verify that user accounts exist
        if (!userDataAccessObject.existById(sharedSignupData.getUser1Id()) ||
                !userDataAccessObject.existById(sharedSignupData.getUser2Id())) {
            presenter.prepareFailView("Both user accounts must exist before creating a shared account.");
            return;
        }

        // Create a new shared account
        SharedAccount newSharedAccount = accountFactory.createSharedAccount(
                sharedSignupData.getSharedAccountId(),
                sharedSignupData.getSharedPassword()
        );

        // Add user IDs to the shared account
        newSharedAccount.addUserIdentification(sharedSignupData.getUser1Id());
        newSharedAccount.addUserIdentification(sharedSignupData.getUser2Id());

        // Add additional users
        Set<String> additionalUserIds = sharedSignupData.getAdditionalUserIds();
        for (String userId : additionalUserIds) {
            newSharedAccount.addUserIdentification(userId);
        }

        // Save the new shared account
        sharedDataAccessObject.save(newSharedAccount);

        // Prepare success view for shared account creation
        SignupOutputData signupOutputData = new SignupOutputData(
                sharedSignupData.getSharedAccountId(), // Shared account ID for success message
                true
        );

        presenter.prepareSuccessView(signupOutputData);
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

