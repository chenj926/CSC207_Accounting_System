package use_case.signup;

import data_access.account.SharedAccountDataAccessInterface;
import data_access.authentication.SharedAccountSignupDataAccessInterface;
import entity.account.SharedAccount;
import entity.account.AccountFactory;

import java.util.Set;

/**
 * The SharedAccountSignupInteractor class handles the signup process for shared accounts.
 * It validates input data, interacts with the data access layer, and uses the presenter to prepare output views.
 * This class does not extend UserAccountSignupInteractor but follows a similar process.
 * <p>
 * This class assumes that it will receive a SharedAccountSignupInputData type as input.
 * </p>
 *
 * @author Xile Chen, Eric Chen
 */
public class SharedAccountSignupInteractor extends SignupInteractor<
        SharedAccountSignupDataAccessInterface,
        SharedAccountSignupInputData> implements SharedAccountSignupInputBoundary {
    private final SharedAccountSignupOutputBoundary presenter;
    private final SharedAccountDataAccessInterface sharedDataAccessObject;

    /**
     * Constructs a SharedAccountSignupInteractor object with the specified data access interfaces,
     * output boundary, and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for user data
     * @param sharedDataAccessObject        the data access interface for shared accounts
     * @param signupOutputBoundary          the output boundary for presenting the signup results
     * @param accountFactory                the factory for creating user accounts
     */
    public SharedAccountSignupInteractor(SharedAccountSignupDataAccessInterface userSignupDataAccessInterface,
                                         SharedAccountDataAccessInterface sharedDataAccessObject,
                                         SharedAccountSignupOutputBoundary signupOutputBoundary,
                                         AccountFactory accountFactory) {
        super(userSignupDataAccessInterface, accountFactory);
        this.sharedDataAccessObject = sharedDataAccessObject;
        this.presenter = signupOutputBoundary;
    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param sharedSignupData the input data required for the signup process
     */
    @Override
    public void execute(SharedAccountSignupInputData sharedSignupData) {
        Set<String> userIds = sharedSignupData.getUserIds();
        String shareAccountId = sharedSignupData.getId();

        boolean validSharedAccountId = checkIdentification(shareAccountId);
        boolean validSharedPassword = checkPassword(sharedSignupData.getPassword());
        boolean validUserIds = this.checkUserIds(userIds);

//
//        boolean validUser1Id = checkIdentification(sharedSignupData.getUser1Id());
//        boolean validUser2Id = checkIdentification(sharedSignupData.getUser2Id());

        if (!validSharedAccountId || !validSharedPassword || !validUserIds) {
            presenter.prepareFailView("All fields must be filled out!");
            return;
        }

        // Check if the shared account already exists
        if (sharedDataAccessObject.existById(shareAccountId)) {
            presenter.prepareFailView("A shared account with this ID already exists!");
            return;
        }

        // Verify that user accounts exist
        boolean userIdsExist = this.checkUserIdsExist(userIds);
        if (!userIdsExist) {
            presenter.prepareFailView("All user accounts must exist before creating a shared account!");
            return;
        }

        // Create a new shared account
        SharedAccount newSharedAccount = accountFactory.createSharedAccount(
                shareAccountId,
                userIds,
                sharedSignupData.getPassword()
        );
        // update the user account ids into newSharedAccount
        this.addIdToShareAccount(newSharedAccount, userIds);

        // Save the new shared account
        sharedDataAccessObject.save(newSharedAccount);

        // Prepare success view for shared account creation
        SharedAccountSignupOutputData outputData = new SharedAccountSignupOutputData(
                shareAccountId,
                sharedSignupData.getUserIds());

        presenter.prepareSuccessView(outputData);
    }

    private boolean checkUserIds(Set<String> userIds) {
        for (String userId : userIds) {
            if (!this.checkIdentification(userId)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkUserIdsExist(Set<String> userIds) {
        for (String userId : userIds) {
            if (!this.userDataAccessObject.existById(userId)) {
                return false;
            }
        }
        return true;
    }

    private void addIdToShareAccount(SharedAccount sharedAccount, Set<String> userIds) {
        for (String userId : userIds) {
            sharedAccount.addUserIdentification(userId);
        }
    }
}

