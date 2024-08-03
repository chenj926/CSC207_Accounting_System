package use_case.login;

/**
 * The SharedAccountLoginOutputData class represents the output data of a shared account login operation.
 * It includes the identification of the user, the shared account ID, and the success status of the login attempt.
 *
 */
public class SharedAccountLoginOutputData extends LoginOutputData {
    private final String sharedAccountId;

    /**
     * Constructs a SharedAccountLoginOutputData object with the specified identification, shared account ID, and success status.
     *
     * @param identification the identification of the user
     * @param sharedAccountId the identification of the shared account
     * @param success        the success status of the login attempt
     */
    public SharedAccountLoginOutputData(String identification, String sharedAccountId, boolean success) {
        super(identification, success);
        this.sharedAccountId = sharedAccountId;
    }

    /**
     * Gets the shared account ID for the login.
     *
     * @return the shared account ID for the login
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }
}

