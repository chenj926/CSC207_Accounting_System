package interface_adaptors;

public class LogoutState {
    private String userId = "";
    private String logoutMessage = "confirm to logout";
    private boolean isLoggedOut = false;

    public LogoutState(LogoutState copy) {
        userId = copy.userId;
        logoutMessage = copy.logoutMessage;
        isLoggedOut = copy.isLoggedOut;
    }

    //the default constructor
    public LogoutState() {}

    public String getUserId() {
        return userId;
    }

    public String getLogoutMessage() {
        return logoutMessage;
    }

    public boolean isLoggedOut() {
        return isLoggedOut;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLogoutMessage(String logoutMessage) {
        this.logoutMessage = logoutMessage;
    }

    public void setLoggedOut(boolean isLoggedOut) {
        this.isLoggedOut = isLoggedOut;
    }
}