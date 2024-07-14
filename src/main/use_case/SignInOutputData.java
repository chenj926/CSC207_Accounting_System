package use_case;

public class SignInOutputData {
    private final String username;
    private final boolean useCaseFailed;

    // Constructor
    public SignInOutputData(String username, boolean useCaseFailed) {
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