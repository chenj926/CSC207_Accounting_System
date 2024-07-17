package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogoutViewModel extends ViewModel {
    public final String titleLabel = "LogoutView";
    public final String logoutButtonText = "Logout";
    public final String cancelButtonText = "Cancel";
    private LogoutState state = new LogoutState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

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
}