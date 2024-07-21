package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {

    private final String TITLE_LABEL = "Sign Up";
    private final String USERNAME_LABEL = "Set username";
    private final String PASSWORD_LABEL = "Set password";
    private final String ID_LABEL = "Set identification";

    private final String SIGNUP_BUTTON_LABEL = "Sign up";
    private final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    // label getters
    public String getTitleLabel() { return this.TITLE_LABEL; }
    public String getUsernameLabel() { return this.USERNAME_LABEL; }
    public String getPasswordLabel() { return this.PASSWORD_LABEL; }
    public String getID_LABEL() { return this.ID_LABEL; }
    public String getSignupButtonLabel(){ return this.SIGNUP_BUTTON_LABEL; }
    public String getCancelButtonLabel() { return this.CANCEL_BUTTON_LABEL; }
    public SignupState getState() { return state; }

    // setters
    public void setState(SignupState state) {
        this.state = state;
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    // 我觉得应该用abstract 的method
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}