package interface_adaptors.transaction.one_time;

import java.util.HashSet;
import java.util.Set;

/**
 * The SharedAccountOneTimeTransactionState class manages the state for the shared account one-time transaction.
 * It keeps track of the user IDs responsible for the transaction and provides methods for state management.
 *
 * This class is a part of the view model state management and helps in encapsulating the transaction-related data.
 *
 */
public class SharedAccountOneTimeTransactionState extends OneTimeTransactionState {
    private String responsibleUserIds;
    private String shareId;

    /**
     * Constructs a SharedAccountOneTimeTransactionState with an empty set of responsible user IDs.
     */
    public SharedAccountOneTimeTransactionState() {
        this.responsibleUserIds = "";
        this.shareId = "";
    }

    /**
     * Gets the set of responsible user IDs for the transaction.
     *
     * @return the set of responsible user IDs
     */
    public String getResponsibleUserIds() {
        return this.responsibleUserIds;
    }

    public String getShareId() {
        return this.shareId;
    }

    /**
     * Sets the responsible user IDs for the transaction.
     *
     * @param responsibleUserIds the set of responsible user IDs
     */
    public void setResponsibleUserIds(String responsibleUserIds) {
        this.responsibleUserIds = responsibleUserIds;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }
}

