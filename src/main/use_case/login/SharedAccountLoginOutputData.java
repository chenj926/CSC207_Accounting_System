package use_case.login;

/**
 * The SharedAccountLoginOutputData class represents the output data of a shared account login operation.
 * It includes the identification of the user, the shared account ID, and the success status of the login attempt.
 *
 */
public class SharedAccountLoginOutputData extends LoginOutputData {

    /**
     * Constructs a SharedAccountLoginOutputData object with the specified identification, shared account ID, and success status.
     *
     * @param sharedAccountId the identification of the shared account
     * @param success        the success status of the login attempt
     */
    public SharedAccountLoginOutputData(String sharedAccountId, boolean success) {
        super(sharedAccountId, success);
    }

//    /**
//     * Gets the shared account ID for the login.
//     *
//     * @return the shared account ID for the login
//     */
//    public String getSharedAccountId() {
//        return this.sharedAccountId;
//    }
//
//    /**
//     * Checks if the login attempt was successful.
//     *
//     * @return true if the login attempt was successful, false otherwise
//     */
//    public boolean isSuccess() {
//        return this.success;
//    }
}

