package use_case.login;

/**
 * The SharedAccountLoginInputData class extends LoginInputData to include additional fields
 * specific to shared account login operations.
 * It includes the shared account ID as part of the login input data.
 */
public class SharedAccountLoginInputData {
    private final String sharedAccountId;
    private final String sharedPassword;

    /**
     * Constructs a SharedAccountLoginInputData object with the specified password,
     * identification, and shared account ID.
     *
     * @param sharedAccountId the shared account ID for the login
     * @param sharedPassword the shared account password for login
     */
    public SharedAccountLoginInputData(String sharedAccountId, String sharedPassword) {
        this.sharedAccountId = sharedAccountId;
        this.sharedPassword = sharedPassword;
    }

    /**
     * Gets the shared account ID for the login.
     *
     * @return the shared account ID for the login
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }

    /**
     * Gets the shared account password for the login.
     *
     * @return the shared account password for the login
     */
    public String getSharedPassword() {
        return this.sharedPassword;
    }
}

