package use_case.signup;

/**
 * The SharedAccountSignupInputData class represents the input data for a shared account signup operation.
 * It extends SignupInputData to include a shared account ID.
 */
public class SharedAccountSignupInputData extends SignupInputData {
    private final String sharedAccountId;

    /**
     * Constructs a SharedAccountSignupInputData object with the specified username, password, identification,
     * and shared account ID.
     *
     * @param username         the username of the user
     * @param password         the password of the user
     * @param identification   the identification of the user
     * @param sharedAccountId  the shared account ID for the signup
     */
    public SharedAccountSignupInputData(String username, String password, String identification, String sharedAccountId) {
        super(username, password, identification);
        this.sharedAccountId = sharedAccountId;
    }

    /**
     * Gets the shared account ID for the signup.
     *
     * @return the shared account ID
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }
}

