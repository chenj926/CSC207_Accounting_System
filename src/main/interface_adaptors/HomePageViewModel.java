package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomePageViewModel extends ViewModel{

    private final String titleLabel = "Home Page";
    private final String signupButtonLabel = "Sign up";
    private final String loginButtonLabel = "Login";
    private final String exitButtonLabel = "Exit";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    // private HomePageState state = new HomePageState();

    public HomePageViewModel() {
        super("Home Page");
    }

    // getters
    public String getTitleLabel() {
        return this.titleLabel;
    }
    public String getSignupButtonLabel() {
        return this.signupButtonLabel;
    }
    public String getLoginButtonLabel() {
        return this.loginButtonLabel;
    }
    public String getExitButtonLabel() {
        return this.exitButtonLabel;
    }

    // public HomePageState getState() {
      //   return this.state;}

    // setter
    // public void setState(HomePageState state) { this.state = state; }




    //
    // public void firePropertyChanged() {
    //    support.firePropertyChange("state", null);}

    // public void addPropertyChangeListener(PropertyChangeListener listener) {
    //    support.addPropertyChangeListener(listener);
    }
