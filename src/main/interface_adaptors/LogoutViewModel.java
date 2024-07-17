package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogoutViewModel extends ViewModel {
    private final String titleLabel = "LogoutView";
    private final String logoutButtonText = "Logout";
    private final String cancelButtonText = "Cancel";
    private LogoutState state = new LogoutState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // label getters
    public String getTitleLabel() {
        return titleLabel;
    }
    public String getLogoutButtonText() {
        return logoutButtonText;
    }
    public String getCancelButtonText() {
        return cancelButtonText;
    }

    public LogoutViewModel() {
        super("logout");
    }
    public void setState(LogoutState state) {
        this.state = state;
    }

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    // Getter
    public LogoutState getState() {
        return state;
    }

}