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
        if (username == null || username.isEmpty()) {
            this.usernameError = "Username cannot be empty";
        } else {
            this.usernameError = null;
        }
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
        validatePassword();
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    private void validatePassword() {
        if (password == null || password.isEmpty()) {
            this.passwordError = "Password cannot be empty";
        } else {
            this.passwordError = null;
        }
    }

    public boolean isValid() {
        return usernameError == null && passwordError == null;
    }

    public void reset() {
        this.identification = "";
        this.identificationError = null;
        this.username = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;

    }
}
