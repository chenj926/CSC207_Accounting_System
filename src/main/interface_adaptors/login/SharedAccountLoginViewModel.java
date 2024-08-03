package interface_adaptors.login;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The SharedAccountLoginViewModel class extends LoginViewModel to manage the state and labels
 * for the shared account login view. It adds support for shared account-specific information.
 */
public class SharedAccountLoginViewModel extends LoginViewModel {

    private final String sharedAccountIdLabel = "Enter shared account ID";
    private SharedAccountLoginState state = new SharedAccountLoginState();

    /**
     * Constructs a SharedAccountLoginViewModel object with the view name set to "shared account login".
     */
    public SharedAccountLoginViewModel() {
        super();
    }

    /**
     * Gets the shared account ID label.
     *
     * @return the shared account ID label
     */
    public String getSharedAccountIdLabel() {
        return this.sharedAccountIdLabel;
    }

    /**
     * Gets the current shared account login state.
     *
     * @return the current shared account login state
     */
    @Override
    public SharedAccountLoginState getState() {
        return this.state;
    }

    /**
     * Sets the current shared account login state.
     *
     * @param state the new shared account login state
     */
    public void setState(SharedAccountLoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies listeners that the shared account login state has changed.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
