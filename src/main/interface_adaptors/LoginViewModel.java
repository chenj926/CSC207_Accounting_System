package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {

    private final String titleLabel = "Log In View";
    private final String usernameLabel = "Enter username";
    private final String passwordLabel = "Enter password";

    private final String loginButtonLabel = "Log in";
    private final String cancelButtonLabel = "Cancel";

    // label getters
    public String getTitleLabel(){
        return this.titleLabel;
    }
    public String getUsernameLabel(){
        return usernameLabel;
    }
    public String getPasswordLabel(){
        return passwordLabel;
    }
    public String getLoginButtonLabel(){
        return loginButtonLabel;
    }
    public String getCancelButtonLabel(){
        return cancelButtonLabel;
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