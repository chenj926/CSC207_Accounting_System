package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {

    private final String TITLE_LABEL = "Log In View";
    private final String USERNAME_LABEL = "Enter username";
    private final String PASSWORD_LABEL = "Enter password";

    private final String LOGIN_BUTTON_LABEL = "Log in";
    private final String CANCEL_BUTTON_LABEL = "Cancel";

    // label getters
    public String getTitleLabel(){
        return TITLE_LABEL;
    }
    public String getUsernameLabel(){
        return USERNAME_LABEL;
    }
    public String getPasswordLabel(){
        return PASSWORD_LABEL;
    }
    public String getLoginButtonLabel(){
        return LOGIN_BUTTON_LABEL;
    }
    public String getCancelButtonLabel(){
        return CANCEL_BUTTON_LABEL;
    }

    private LoginState state = new LoginState();

    public LoginViewModel() {
        super("log in");
    }

    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoginState getState() {
        return state;
    }
}