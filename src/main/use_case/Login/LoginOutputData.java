package use_case.Login;

/**
 * The LoginOutputData class represents the output data of a login operation.
 * It includes the identification of the user and the success status of the login attempt.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public class LoginOutputData {
    private final String identification;
    private final boolean success;

    /**
     * Constructs a LoginOutputData object with the specified identification and success status.
     *
     * @param identification the identification of the user
     * @param success        the success status of the login attempt
     */
    public LoginOutputData(String identification, boolean success) {
        this.identification = identification;
        this.success = success;
    }

    /**
     * Checks if the login attempt was successful.
     *
     * @return true if the login attempt was successful, false otherwise
     */
    public String getIdentification() {
        return this.identification;
    }

    /**
     * Checks if the login attempt was successful.
     *
     * @return true if the login attempt was successful, false otherwise
     */
    public boolean isSuccess() {
        return this.success;
    }
}