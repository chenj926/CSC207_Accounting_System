package interface_adaptors.login;

/**
 * The SharedAccountLoginState class extends LoginState to add properties
 * and methods specific to the shared account login process.
 */
public class SharedAccountLoginState{
    private String sharedAccountId;
    private String sharedAccountPassword;
    private String stateError;
    private String successMsg;

    /**
     * Constructs a SharedAccountLoginState object with default values.
     */
    public SharedAccountLoginState() {
        this.sharedAccountId = "";
        this.sharedAccountPassword = "";
        this.stateError = null;
        this.successMsg = null;
    }

    /**
     * Gets the shared account ID.
     *
     * @return the shared account ID
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }

    /**
     * Sets the shared account ID.
     *
     * @param sharedAccountId the new shared account ID
     */
    public void setSharedAccountId(String sharedAccountId) {
        this.sharedAccountId = sharedAccountId;
    }

    public String getSharedAccountPassword() {
        return this.sharedAccountPassword;
    }

    public void setSharedAccountPassword(String sharedAccountPassword) {
        this.sharedAccountPassword = sharedAccountPassword;
    }

    public String getStateError() {
        return this.stateError;
    }

    public void setStateError(String stateError) {
        this.stateError = stateError;
    }

    public String getSuccessMsg() {
        return this.successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
}

