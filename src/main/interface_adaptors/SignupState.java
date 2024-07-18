package interface_adaptors;

public class SignupState {
    private String identification;
    private String identificationError;
    private String username;
    private String usernameError;
    private String password;
    private String passwordError;

    public SignupState() {
        this.identification = "";
        this.identificationError = null;
        this.username = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;
    }

    public String getIdentification() { return this.identification; }

    public String getIdentificationError() { return this.identificationError; }

    public String getUsername() {
        return this.username;
    }

    public String getUsernameError() {
        return this.usernameError;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPasswordError() {
        return this.passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setIdentificationError(String identificationError) { this.identificationError = identificationError; }

    public boolean isValid() {
        return usernameError == null && passwordError == null;
    }

    public void reset() {
        this.identification = "";
        this.setIdentificationError(null);
        this.setUsername("");
        this.setUsernameError(null);
        this.setPassword("");
        this.setPasswordError(null);

    }
}
