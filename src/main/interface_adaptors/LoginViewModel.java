package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {

    private final String titleLabel = "LOGIN";
    private final String identificationLabel = "Enter identification";
    private final String passwordLabel = "Enter password";

    private final String loginButtonLabel = "Login";
    private final String cancelButtonLabel = "Cancel";

    private LoginState state = new LoginState();

    public LoginViewModel() {
        super("login");
    }

    // getters
    public String getTitleLabel(){
        return this.titleLabel;
    }
    public String getIdentificationLabel(){
        return identificationLabel;
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
    public LoginState getState() {
        return state;
    }
    // setters
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


}