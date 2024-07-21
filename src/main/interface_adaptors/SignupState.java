package interface_adaptors;

public class SignupState {
    private String identification;
    private String username;
    private String password;
    private String stateError;
    private String successMsg;

    public SignupState() {
        this.identification = "";
        this.username = "";
        this.password = "";
        this.stateError = null;
        this.successMsg = null;
    }

    // getters
    public String getIdentification() { return this.identification; }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getStateError() { return this.stateError; }

    public String getSuccessMsg() { return this.successMsg; }

    // setters
    public void setUsername(String username) { this.username = username;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdentification(String identification) { this.identification = identification; }

    public void setStateError(String err) { this.stateError = err; }

    public void setSuccessMsg(String msg) { this.successMsg = msg; }

    public boolean isValid() {
        return this.successMsg != null;
    }

    public void reset() {
        this.identification = "";
        this.setUsername("");
        this.setPassword("");
        this.setStateError(null);
        this.setSuccessMsg(null);
    }
}
