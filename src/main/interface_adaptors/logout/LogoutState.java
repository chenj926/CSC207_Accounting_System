package interface_adaptors.logout;

/**
 * The LogoutState class represents the state of the logout process,
 * including logout messages and error messages.
 *
 * @author Dana
 */
public class LogoutState {
    private String logoutMessage;
    private String stateError;

    /**
     * Constructs a LogoutState object with default values.
     */
    public LogoutState() {
        this.logoutMessage = null;
        this.stateError = null;
    }

    /**
     * Gets the logout message.
     *
     * @return the logout message
     */
    public String getLogoutMessage() {
        return this.logoutMessage;
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
     * Sets the logout message.
     *
     * @param logoutMessage the new logout message
     */
    public void setLogoutMessage(String logoutMessage) {
        this.logoutMessage = logoutMessage;
    }

    /**
     * Sets the state error message.
     *
     * @param stateError the new state error message
     */
    public void setStateError(String stateError) { this.stateError = stateError; }
}