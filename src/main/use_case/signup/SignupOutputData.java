package use_case.signup;

/**
 * The SignupOutputData class serves as a base class for all types of signup output data.
 * It contains common fields such as username and the status of whether the use case has failed.
 */
public class SignupOutputData {
    private final String username;
    private final boolean useCaseFailed;

    /**
     * Constructs a SignupOutputData object with the specified username and failure status.
     *
     * @param username      the username of the newly signed-up user
     * @param useCaseFailed the status indicating if the signup use case has failed
     */
    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username of the newly signed-up user.
     *
     * @return the username of the newly signed-up user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Checks if the signup use case has failed.
     *
     * @return true if the signup use case has failed, false otherwise
     */
    public boolean isUseCaseFailed() {
        return this.useCaseFailed;
    }
}
