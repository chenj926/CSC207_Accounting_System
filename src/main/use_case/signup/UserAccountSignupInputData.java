package use_case.signup;

/**
 * The UserAccountSignupInputData class represents the base input data required for a signup operation.
 * It includes the username, password, and identification of the user.
 * This class serves as a superclass for specific signup types.
 */
public class UserAccountSignupInputData {
    private final String username;
    private final String password;
    private final String identification;

    /**
     * Constructs a UserAccountSignupInputData object with the specified username, password, and identification.
     *
     * @param username       the username of the user
     * @param password       the password of the user
     * @param identification the identification of the user
     */
    public UserAccountSignupInputData(String username, String password, String identification) {
        this.username = username;
        this.password = password;
        this.identification = identification;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the identification of the user.
     *
     * @return the identification of the user
     */
    public String getIdentification() {
        return this.identification;
    }
}
