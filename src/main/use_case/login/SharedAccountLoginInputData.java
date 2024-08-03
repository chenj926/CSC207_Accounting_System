package use_case.login;

/**
 * The SharedAccountLoginInputData class extends LoginInputData to include additional fields
 * specific to shared account login operations.
 * It includes the shared account ID as part of the login input data.
 */
public class SharedAccountLoginInputData extends LoginInputData {
    private final String sharedAccountId;

    /**
     * Constructs a SharedAccountLoginInputData object with the specified password,
     * identification, and shared account ID.
     *
     * @param password        the password for the login
     * @param identification  the identification for the login
     * @param sharedAccountId the shared account ID for the login
     */
    public SharedAccountLoginInputData(String password, String identification, String sharedAccountId) {
        super(password, identification);
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

