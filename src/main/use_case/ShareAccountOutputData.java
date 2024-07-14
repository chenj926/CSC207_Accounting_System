package use_case;

import java.util.*;

public class ShareAccountOutputData {
    private final Set<String> id;
    private final String shareAccountId;
    private boolean shareAccountFailed;

    // Constructor
    public ShareAccountOutputData(String shareAccountId, Set<String> id, boolean shareAccountFailed) {
        this.id = id;
        this.shareAccountFailed = shareAccountFailed;
        this.shareAccountId = shareAccountId;
    }

    // Getters
    public Set<String> getId() {
        return id;
    }
    public boolean isShareAccountFailed() {
        return shareAccountFailed;
    }
    public String getShareAccountId() {return shareAccountId; }
}