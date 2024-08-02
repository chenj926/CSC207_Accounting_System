package interface_adaptors.signup;


/**
 * The SharedAccountSignupViewModel class extends SignupViewModel to add properties and methods specific to shared account signup.
 * It provides additional labels and state management for the shared account signup view.
 */
public class SharedAccountSignupViewModel extends SignupViewModel {

    private final String SHARED_ACCOUNT_ID_LABEL = "Set shared account ID";

    private SharedAccountSignupState state = new SharedAccountSignupState();

    /**
     * Constructs a SharedAccountSignupViewModel object with the view name set to "shared account sign up".
     */
    public SharedAccountSignupViewModel(String viewname) {
        super("shared account sign up");
    }

    /**
     * Gets the shared account ID label.
     *
     * @return the shared account ID label
     */
    public String getSharedAccountIdLabel() {
        return this.SHARED_ACCOUNT_ID_LABEL;
    }

    /**
     * Gets the current shared account signup state.
     *
     * @return the current shared account signup state
     */
    @Override
    public SharedAccountSignupState getState() {
        return state;
    }

    /**
     * Sets the current shared account signup state.
     *
     * @param state the new shared account signup state
     */
    public void setState(SharedAccountSignupState state) {
        this.state = state;
    }

    /**
     * Notifies listeners that the shared account signup state has changed.
     */
    @Override
    public void firePropertyChanged() {
        super.firePropertyChanged();
    }
}
