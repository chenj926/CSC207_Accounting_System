package interface_adaptors;

public class LogoutState {
    private String logoutMessage;
    private String stateError;

    public LogoutState() {
        this.logoutMessage = null;
        this.stateError = null;
    }

    //the default constructor

// getter
    public String getLogoutMessage() {
        return this.logoutMessage;
    }

    public String getStateError() {
        return this.stateError;
    }

// setter
    public void setLogoutMessage(String logoutMessage) {
        this.logoutMessage = logoutMessage;
    }

    public void setStateError(String stateError) { this.stateError = stateError; }
}