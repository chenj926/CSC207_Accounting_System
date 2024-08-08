package interface_adaptors.signup;

/**
 * The UserAccountSignupState class represents the state of the signup process,
 * including user details, error messages, and success messages.
 *
 * @author Chi Fong
 * @author Eric
 */
public class UserAccountSignupState  extends AccountSignupState{
    private String username;

    /**
     * Constructs a UserAccountSignupState object with default values.
     */
    public UserAccountSignupState() {
        super();
        this.username = "";
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) { this.username = username;}

    /**
     * Resets the signup state to default values.
     */
    public void reset() {
        super.reset();
        this.setUsername("");
    }
}
