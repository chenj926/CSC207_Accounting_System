package use_case;

public class LoginInputData {
    private final String password;
    private final String identification;

    // Constructor
    public LoginInputData(String password, String identification){
        this.password = password;
        this.identification = identification;
    }

    // getter
    public String getPassword() {
        return this.password;
    }
    public String getIdentification() {
        return this.identification;
    }
`}