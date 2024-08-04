package interface_adaptors.signup;

import use_case.signup.SharedAccountSignupInputBoundary;
import use_case.signup.SharedAccountSignupInputData;
import use_case.signup.SharedAccountSignupInteractor;

import java.util.Set;

/**
 * The SharedAccountSignupController class handles user input for the shared account signup process.
 * It creates an input data object and delegates the execution to the signup interactor.
 */
public class SharedAccountSignupController {
    final SharedAccountSignupInputBoundary sharedAccountSignupInteractor;

    /**
     * Constructs a SharedAccountSignupController with the specified signup interactor.
     *
     * @param sharedAccountSignupInteractor the interactor that processes the signup data
     */
    public SharedAccountSignupController(SharedAccountSignupInputBoundary sharedAccountSignupInteractor) {
        this.sharedAccountSignupInteractor = sharedAccountSignupInteractor;
    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param sharedAccountId       the shared account ID
     * @param sharedAccountPassword the shared account password
     * @param user1Id               the first user's ID
     * @param user2Id               the second user's ID
     * @param additionalUserIds     a set of additional user IDs
     */
    public void execute(String sharedAccountId, String sharedAccountPassword, String user1Id, String user2Id, Set<String> additionalUserIds) {
        // Create input data object for the shared account signup
        SharedAccountSignupInputData sharedAccountSignupInputData = new SharedAccountSignupInputData(
                sharedAccountId,
                sharedAccountPassword,
                user1Id,
                user2Id,
                additionalUserIds
        );

        // Execute the signup process using the interactor
        sharedAccountSignupInteractor.execute(sharedAccountSignupInputData);
    }
}

