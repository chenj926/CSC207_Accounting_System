package use_case.ShareAccount;

import java.util.*;


/**
 * The ShareAccountInputData class represents the input data required for sharing an account.
 * It includes the identification of the shared account and the set of user identifications to be shared.
 *
 * @author Eric
 */
public class ShareAccountInputData {
    private final Set<String> sharedUserIdentifications;
    private final String shareAccountIdentification;

    /**
     * Constructs a ShareAccountInputData object with the specified shared account identification and set of user identifications.
     *
     * @param shareAccountIdentification the identification of the shared account
     * @param sharedUserIdentifications  the set of user identifications to be shared
     */
    public ShareAccountInputData(String shareAccountIdentification,
                                 Set<String> sharedUserIdentifications) {
        this.sharedUserIdentifications = sharedUserIdentifications;
        this.shareAccountIdentification = shareAccountIdentification;
    }

    /**
     * Gets the set of user identifications to be shared.
     *
     * @return the set of user identifications to be shared
     */
    public Set<String> getSharedUserIdentifications() {
        return sharedUserIdentifications;
    }

    /**
     * Gets the identification of the shared account.
     *
     * @return the identification of the shared account
     */
    public String getShareAccountIdentification() { return shareAccountIdentification; }
}