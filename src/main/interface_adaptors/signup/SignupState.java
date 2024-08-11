package interface_adaptors.signup;

/**
 * The UserAccountSignupState class represents the state of the signup process,
 * including user details, error messages, and success messages.
 *
 * @author Chi Fong
 * @author Eric
 */
public abstract class SignupState {
    protected String identification;
    protected String password;

    protected String stateError;
    protected String successMsg;

    /**
     * Constructs a UserAccountSignupState object with default values.
     */
    public SignupState() {
        this.identification = "";
        this.password = "";
        this.stateError = null;
        this.successMsg = null;
    }

    /**
     * Gets the identification.
     *
     * @return the identification
     */
    public String getIdentification() { return this.identification; }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }


    /**
     * Gets the state error message.
     *
     * @return the state error message
     */
    public String getStateError() { return this.stateError; }

    /**
     * Gets the success message.
     *
     * @return the success message
     */
    public String getSuccessMsg() { return this.successMsg; }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the identification.
     *
     * @param identification the new identification
     */
    public void setIdentification(String identification) { this.identification = identification; }


    /**
     * Sets the state error message.
     *
     * @param err the new state error message
     */
    public void setStateError(String err) { this.stateError = err; }

    /**
     * Sets the success message.
     *
     * @param msg the new success message
     */
    public void setSuccessMsg(String msg) { this.successMsg = msg; }

    /**
     * Checks if the signup state is valid (i.e., there is a success message).
     *
     * @return true if the signup state is valid, false otherwise
     */
    public boolean isValid() {
        return this.successMsg != null;
    }

    /**
     * Resets the signup state to default values.
     */
    public void reset() {
        this.identification = "";
        this.setPassword("");
        this.setStateError(null);
        this.setSuccessMsg(null);
    }
}