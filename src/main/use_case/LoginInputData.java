package use_case;

public class SignInInputData {
    private final String username;
    private final String password;
    private final String identification;

    // Constructor
    public SignInInputData(String username, String password, String identification){
        this.username = username;
        this.password = password;
        this.identification = identification;
    }

    // getter
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getIdentification() {
        return this.identification;
    }
}