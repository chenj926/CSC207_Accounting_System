package use_case.login;

import use_case.signup.SignupOutputData;

public abstract class LoginInputData {
    private String identification;
    private String password;

    public LoginInputData(String identification, String password) {
        this.identification = identification;
        this.password = password;
    }

    public String getIdentification() {return this.identification;}

    public void setIdentification(String identification) {this.identification = identification;}

    public String getPassword() {return this.password;}

    public void setPassword(String password) {this.password = password;}
}
