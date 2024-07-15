package use_case;

public class LogInOutputData {
    private final String username;
    private final boolean useCaseFailed;

    // Constructor
    public LogInOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    // Getters
    public String getUsername() {
        return this.username;
    }

    public boolean isUseCaseFailed() {
        return this.useCaseFailed;
    }
}