package use_case.signup.shared_account;

import use_case.signup.SignupOutputData;

import java.util.Set;

/**
 * The SharedAccountSignupOutputData class represents the output data of a shared account signup operation.
 * It includes specific fields such as shared account ID and the status of whether the shared account already exists.
 */
public class SharedAccountSignupOutputData implements SignupOutputData<Set<String>> {
    private final String sharedAccountId;
//    private String user1Id;
//    private String user2Id;
    private Set<String> userIds;
//    private final boolean sharedAccountExists;

    public SharedAccountSignupOutputData(String sharedAccountId, Set<String> userIds) {
        this.sharedAccountId = sharedAccountId;
//        this.user1Id = user1Id;
//        this.user2Id = user2Id;
        this.userIds = userIds;
//        this.sharedAccountExists = sharedAccountExists;
    }

    /**
     * Gets the shared account ID.
     *
     * @return the shared account ID, or null if not applicable
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }
//
//    public String getUser1Id() {return this.user1Id;}
//
//    public String getUser2Id() {return this.user2Id;}

    public Set<String> getUserIds() {
        return this.userIds;
    }

}

    /**
     * Checks if the shared account already exists.
     *
     * @return true if the shared account already exists, false otherwise
     */
//    public boolean isSharedAccountExists() {
//        return this.sharedAccountExists;
//    }
//}

