package use_case.signup;

public abstract class SignupInputData {
    private String identification;
    private String password;

    public SignupInputData(String identification, String password) {
        this.identification = identification;
        this.password = password;
    }

    public String getId() {
        return this.identification;
    }

    public void setId(String identification) {
        this.identification = identification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
