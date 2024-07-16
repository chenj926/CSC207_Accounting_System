package use_case;

public class LogInOutputData {
    private final String username;
    private final boolean success;

    // Constructor
    public LogInOutputData(String username, boolean success) {
        this.username = username;
        this.success = success;
    }

    // Getters
    public String getUsername() {
        return this.username;
    }

    public boolean isSuccess() {
        return this.success;
    }
}