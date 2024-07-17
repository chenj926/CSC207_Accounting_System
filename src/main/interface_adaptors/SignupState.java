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
            this.setUsernameError("Username cannot be empty");
            return;  // if the username is wrong, don't let it be assigned to username
        } else {
            this.setUsernameError(null);
        }
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        // !!!!!!!都tmd应该交给interactor来管！！！！改改改！md

        if (password == null || password.isEmpty()) {
            this.setPasswordError("Password cannot be empty");
            return;  // if the password is wrong, don't let it be assigned to username
        } else {
            this.setPasswordError(null);
        }
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
        this.identificationError = null;
        this.username = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;

    }
}
