//package interface_adaptors.logout;
//
//import interface_adaptors.ViewModel;
//
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
//
///**
// * The LogoutViewModel class extends the ViewModel class and manages the state and labels for the logout view.
// * It provides getters for various labels used in the logout view and supports property change notifications.
// *
// * @author Dana
// * @author Jessica
// */
//public class LogoutViewModel extends ViewModel {
//    private final String titleLabel = "LOGOUT";
//    private final String logoutButtonText = "Logout";
//    private final String cancelButtonText = "Cancel";
//
//    private LogoutState state = new LogoutState();
//
//    /**
//     * Constructs a LogoutViewModel object with the view name set to "logout".
//     */
//    public LogoutViewModel() {
//        super("logout");
//    }
//
//    /**
//     * Gets the title label.
//     *
//     * @return the title label
//     */
//    public String getTitleLabel() {
//        return titleLabel;
//    }
//
//    /**
//     * Gets the logout button text.
//     *
//     * @return the logout button text
//     */
//    public String getLogoutButtonText() {
//        return logoutButtonText;
//    }
//
//    /**
//     * Gets the cancel button text.
//     *
//     * @return the cancel button text
//     */
//    public String getCancelButtonText() {
//        return cancelButtonText;
//    }
//
//    /**
//     * Gets the current logout state.
//     *
//     * @return the current logout state
//     */
//    public LogoutState getState() {
//        return state;
//    }
//
//    /**
//     * Sets the current logout state.
//     *
//     * @param state the new logout state
//     */
//    public void setState(LogoutState state) {
//        this.state = state;
//    }
//
//    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
//
//    /**
//     * Notifies listeners that the logout state has changed.
//     */
//    public void firePropertyChanged(){
//        support.firePropertyChange("state", null, this.state);
//    }
//
//    /**
//     * Notifies listeners that the logout state has changed.
//     */
//    public void addPropertyChangeListener(PropertyChangeListener listener){
//        support.addPropertyChangeListener(listener);
//    }
//
//}