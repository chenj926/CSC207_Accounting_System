package interface_adaptors;

public class LogoutState {
    private String logoutMessage;
    private boolean isLoggedOut = false;

    public LogoutState() {
        this.logoutMessage = null;
        this.isLoggedOut =  false;
    }

    //the default constructor


    public String getLogoutMessage() {
        return logoutMessage;
    }

    public boolean isLoggedOut() {
        return isLoggedOut;
    }


    public void setLogoutMessage(String logoutMessage) {
        this.logoutMessage = logoutMessage;
    }

    public void setLoggedOut(boolean isLoggedOut) {
        this.isLoggedOut = isLoggedOut;
    }
}