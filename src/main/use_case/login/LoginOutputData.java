package use_case.login;

public class LoginOutputData {
    private String identification;
    private boolean success;
    public LoginOutputData(String identification, boolean success) {
        this.identification = identification;
        this.success = success;
    }
    public String getIdentification() {return this.identification;}
    public boolean isSuccess() {return this.success;}
}
