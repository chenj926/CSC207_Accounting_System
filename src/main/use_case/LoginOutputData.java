package use_case;

public class LoginOutputData {
    private final String identification;
    private final boolean success;

    // Constructor
    public LoginOutputData(String username, boolean success) {
        this.identification = username;
        this.success = success;
    }

    // Getters
    public String getIdentification() {
        return this.identification;
    }

    public boolean isSuccess() {
        return this.success;
    }
}