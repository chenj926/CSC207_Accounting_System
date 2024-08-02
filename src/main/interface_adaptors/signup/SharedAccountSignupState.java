package interface_adaptors.signup;

/**
 * The SharedAccountSignupState class extends SignupState to add shared account-specific information.
 * It includes properties related to the shared account signup process.
 */
public class SharedAccountSignupState extends SignupState {
    private String sharedAccountId;

    // Constructor
    public SharedAccountSignupState() {
        super();
    }

    // Getter and setter for shared account ID
    public String getSharedAccountId() {
        return sharedAccountId;
    }

    public void setSharedAccountId(String sharedAccountId) {
        this.sharedAccountId = sharedAccountId;
    }

    // Overridden method to check validity with shared account ID
    @Override
    public boolean isValid() {
        return super.isValid() && sharedAccountId != null;
    }
}
