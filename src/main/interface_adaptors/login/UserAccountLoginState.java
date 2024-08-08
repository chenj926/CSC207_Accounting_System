package interface_adaptors.login;

/**
 * The UserLoginState class represents the state of the login process,
 * including user details, error messages, and success messages.
 *
 * @author Chi Fong
 * @author Eric
 */
public class UserAccountLoginState {
    private String identification;
    private String password;
    private String stateError;
    private String successMsg;

    /**
     * Constructs a LoginState object with default values.
     */
    public UserAccountLoginState() {
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
    public String getIdentification() {
        return this.identification;
    }

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
    public String getStateError() {
        return this.stateError;
    }

    /**
     * Gets the success message.
     *
     * @return the success message
     */
    public String getSuccessMsg() {
        return this.successMsg;
    }

    /**
     * Sets the identification.
     *
     * @param id the new identification
     */
    public void setIdentification(String id) {
        this.identification = id;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the state error message.
     *
     * @param err the new state error message
     */
    public void setStateError(String err) {
        this.stateError = err;
    }

    /**
     * Sets the success message.
     *
     * @param msg the new success message
     */
    public void setSuccessMsg(String msg) {
        this.successMsg = msg;
    }
}
