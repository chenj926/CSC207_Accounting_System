package interface_adaptors.login;

/**
 * The SharedAccountLoginState class extends LoginState to add properties
 * and methods specific to the shared account login process.
 */
public class SharedAccountLoginState extends LoginState {
    private String sharedAccountId;

    /**
     * Constructs a SharedAccountLoginState object with default values.
     */
    public SharedAccountLoginState() {
        super();
        this.sharedAccountId = "";
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
}

