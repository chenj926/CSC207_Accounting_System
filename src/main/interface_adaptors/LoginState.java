package interface_adaptors;

public class LoginState {
    private String identification;
    private String password;
    private String stateError;
    private String successMsg;

    public LoginState() {
        this.identification = "";
        this.password = "";
        this.stateError = null;
        this.successMsg = null;
    }

    // getters
    public String getIdentification() {
        return this.identification;
    }

    public String getPassword() {
        return this.password;
    }

    public String getStateError() {
        return this.stateError;
    }

    public String getSuccessMsg() {
        return this.successMsg;
    }

    // setters
    public void setIdentification(String id) {
        this.identification = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStateError(String err) {
        this.stateError = err;
    }

    public void setSuccessMsg(String msg) {
        this.successMsg = msg;
    }
}
