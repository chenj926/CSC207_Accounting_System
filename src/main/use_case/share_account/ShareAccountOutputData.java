//package use_case.share_account;
//
//import java.util.*;
//
//
///**
// * The ShareAccountOutputData class represents the output data of a share account operation.
// * It includes the shared user identifications, the shared account identification, and the status of whether the share account operation has failed.
// *
// * @author Eric
// */
//public class ShareAccountOutputData {
//    private final Set<String> id;
//    private final String shareAccountId;
//    private boolean shareAccountFailed;
//
//    /**
//     * Constructs a ShareAccountOutputData object with the specified shared account identification,
//     * user identifications, and failure status.
//     *
//     * @param shareAccountId       the identification of the shared account
//     * @param id                   the set of user identifications
//     * @param shareAccountFailed   the status indicating if the share account use case has failed
//     */
//    public ShareAccountOutputData(String shareAccountId, Set<String> id, boolean shareAccountFailed) {
//        this.id = id;
//        this.shareAccountFailed = shareAccountFailed;
//        this.shareAccountId = shareAccountId;
//    }
//
//    /**
//     * Gets the set of user identifications.
//     *
//     * @return the set of user identifications
//     */
//    public Set<String> getId() {
//        return id;
//    }
//
//    /**
//     * Checks if the share account use case has failed.
//     *
//     * @return true if the share account use case has failed, false otherwise
//     */
//    public boolean isShareAccountFailed() {
//        return shareAccountFailed;
//    }
//
//    /**
//     * Gets the identification of the shared account.
//     *
//     * @return the identification of the shared account
//     */
//    public String getShareAccountId() {return shareAccountId; }
//}