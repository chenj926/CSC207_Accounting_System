package use_case.login;

/**
 * The SharedAccountLoginInputData class extends LoginInputData to include additional fields
 * specific to shared account login operations.
 * It includes the shared account ID as part of the login input data.
 */
public class SharedAccountLoginInputData extends LoginInputData {

    /**
     * Constructs a SharedAccountLoginInputData object with the specified password,
     * identification, and shared account ID.
     *
     * @param sharedAccountId the shared account ID for the login
     * @param sharedPassword the shared account password for login
     */
    public SharedAccountLoginInputData(String sharedAccountId, String sharedPassword) {
        super(sharedAccountId, sharedPassword);
    }
}

