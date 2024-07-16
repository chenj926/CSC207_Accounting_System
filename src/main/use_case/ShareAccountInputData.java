package use_case;

import java.util.*;

public class ShareAccountInputData {
    private final Set<String> sharedUserIdentifications;
    private final String shareAccountIdentification;

    // Constructor
    public ShareAccountInputData(String shareAccountIdentification,
                                 Set<String> sharedUserIdentifications) {
        this.sharedUserIdentifications = sharedUserIdentifications;
        this.shareAccountIdentification = shareAccountIdentification;
    }

    // Getters
    public Set<String> getSharedUserIdentifications() {
        return sharedUserIdentifications;
    }
    public String getShareAccountIdentification() { return shareAccountIdentification; }
}