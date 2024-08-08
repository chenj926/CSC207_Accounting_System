package interface_adaptors.signup;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * The SharedAccountSignupViewModel class extends UserAccountSignupViewModel to add properties and methods specific to shared account signup.
 * It provides additional labels and state management for the shared account signup view.
 */
public class SharedAccountSignupViewModel extends AccountSignupViewModel<SharedAccountSignupState> {

    private final String TITLE_LABEL = "Shared Account Sign Up";
    private final String ID_LABEL = "Set shared account id";
    private final String PASSWORD_LABEL = "Set shared account password";

    private final String USER_IDS_LABEL = "Add users";

    // List to hold additional user labels
    private final List<String> additionalUserLabels;

    /**
     * Constructs a SharedAccountSignupViewModel object with the view name set to "shared account sign up".
     */
    public SharedAccountSignupViewModel() {
        super("shared account sign up");
        this.state = new SharedAccountSignupState();
        this.additionalUserLabels = new ArrayList<>();
    }

    /**
     * Gets the identification label.
     *
     * @return the identification label
     */
    public String getUserIdsLabel() { return this.USER_IDS_LABEL; }

    public void addMoreUserLabel() {
        int nextUserNumber = this.getState().getUserId().size();
        String newLabel = "Add user" + nextUserNumber + " ID";
        additionalUserLabels.add(newLabel);
    }

    /**
     * Removes the last additional user label if there are any.
     */
    public void removeLastUserLabel() {
        if (!additionalUserLabels.isEmpty()) {
            additionalUserLabels.remove(additionalUserLabels.size() - 1);
        }
    }

    /**
     * Gets the list of additional user labels.
     *
     * @return the list of additional user labels
     */
    public List<String> getAdditionalUserLabels() {
        return new ArrayList<>(additionalUserLabels);
    }
}
