package interface_adaptors.signup.shared_account;

import interface_adaptors.signup.SignupController;
import use_case.signup.shared_account.SharedAccountSignupInputBoundary;
import use_case.signup.shared_account.SharedAccountSignupInputData;
import use_case.signup.shared_account.SharedAccountSignupInteractor;

import java.util.Set;

/**
 * The SharedAccountSignupController class handles user input for the shared account signup process.
 * It creates an input data object and delegates the execution to the signup interactor.
 *
 * @author Xile Chen, Eric Chen
 */
public class SharedAccountSignupController extends SignupController<
        SharedAccountSignupInputBoundary,
        SharedAccountSignupInteractor> {

    /**
     * Constructs a SharedAccountSignupController with the specified signup interactor.
     *
     * @param sharedAccountSignupInteractor the interactor that processes the signup data
     */
    public SharedAccountSignupController(SharedAccountSignupInteractor sharedAccountSignupInteractor) {
        super(sharedAccountSignupInteractor);
    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param sharedAccountId       the shared account ID
     * @param sharedAccountPassword the shared account password
//     * @param user1Id               the first user's ID
//     * @param user2Id               the second user's ID
     * @param userIds     a set of additional user IDs
     */
    public void execute(String sharedAccountId, String sharedAccountPassword, Set<String> userIds) {
        // Create input data object for the shared account signup
        System.out.println("in controller"+sharedAccountId+"\n"+sharedAccountPassword+"\n"+userIds.toString());
        SharedAccountSignupInputData sharedAccountSignupInputData = new SharedAccountSignupInputData(
                sharedAccountId,
                sharedAccountPassword,
                userIds
        );

        // Execute the signup process using the interactor
        signupInteractor.execute(sharedAccountSignupInputData);
    }
}

