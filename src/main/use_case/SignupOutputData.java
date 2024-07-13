package use_case;

import java.util.*;

public class SignupOutputData {
    private final String username;
    private boolean useCaseFailed;

    // Constructor
    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    // getters
    public String getUsername {
        return this.username;
    }
    public boolean isUseCaseFailed {
        return this.useCaseFailed;
    }
}