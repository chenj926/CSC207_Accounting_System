package use_case;

import java.util.*;

public class ShareAccountInputData {
    private final Set<String> sharedUserIdentifications;

    // Constructor
    public ShareAccountInputData(Set<String> sharedUserIdentifications) {
        this.sharedUserIdentifications = sharedUserIdentifications;
    }

    // Getters
    public Set<String> getSharedUserIdentifications() {
        return sharedUserIdentifications;
    }
}