package use_case;

import java.util.*;

public class ShareAccountOutputData {
    private final Set<String> id;
    private boolean shareAccountFailed;

    // Constructor
    public ShareAccountOutputData(Set<String> id, boolean shareAccountFailed) {
        this.id = id;
        this.shareAccountFailed = shareAccountFailed;
    }

    // Getters
    public Set<String> getId() {
        return id;
    }
    public boolean isShareAccountFailed() {
        return shareAccountFailed;
    }
}