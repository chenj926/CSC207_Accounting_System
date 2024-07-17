package interface_adaptors;

public class SignupState {
    private String username;
    private String usernameError;
    private String password;
    private String passwordError;
    private String repeatPassword;
    private String repeatPasswordError;

    public SignupState() {
        this.username = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;
        this.repeatPassword = "";
        this.repeatPasswordError = null;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setUsername(String username) {
        this.username = username;
        if (username == null || username.isEmpty()) {
            this.usernameError = "Username cannot be empty";
        } else {
            this.usernameError = null;
        }
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

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
        validatePassword();
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    private void validatePassword() {
        if (password == null || password.isEmpty()) {
            this.passwordError = "Password cannot be empty";
        } else {
            this.passwordError = null;
        }

        if (repeatPassword == null || repeatPassword.isEmpty()) {
            this.repeatPasswordError = "Repeat password cannot be empty";
        } else if (!repeatPassword.equals(password)) {
            this.repeatPasswordError = "Passwords do not match";
        } else {
            this.repeatPasswordError = null;
        }
    }

    public boolean isValid() {
        return usernameError == null && passwordError == null && repeatPasswordError == null;
    }

    public void reset() {
        this.username = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;
        this.repeatPassword = "";
        this.repeatPasswordError = null;
    }
}
