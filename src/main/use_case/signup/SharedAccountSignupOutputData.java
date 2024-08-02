package use_case.signup;

/**
 * The SharedAccountSignupOutputData class represents the output data of a shared account signup operation.
 * It includes specific fields such as shared account ID and the status of whether the shared account already exists.
 */
public class SharedAccountSignupOutputData extends SignupOutputData {
    private final String sharedAccountId;
    private final boolean sharedAccountExists;

    /**
     * Constructs a SharedAccountSignupOutputData object with the specified details.
     *
     * @param username            the username of the newly signed-up user
     * @param useCaseFailed       the status indicating if the signup use case has failed
     * @param sharedAccountId     the shared account ID
     * @param sharedAccountExists the status indicating if the shared account already exists
     */
    public SharedAccountSignupOutputData(String username, boolean useCaseFailed, String sharedAccountId, boolean sharedAccountExists) {
        super(username, useCaseFailed);
        this.sharedAccountId = sharedAccountId;
        this.sharedAccountExists = sharedAccountExists;
    }

    /**
     * Gets the shared account ID.
     *
     * @return the shared account ID, or null if not applicable
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }

    /**
     * Checks if the shared account already exists.
     *
     * @return true if the shared account already exists, false otherwise
     */
    public boolean isSharedAccountExists() {
        return this.sharedAccountExists;
    }
}

