package interface_adaptors.signup;

import java.util.HashSet;
import java.util.Set;

/**
 * The SharedAccountSignupState class manages the state information during the shared account signup process.
 * It includes fields for shared account ID, password, user IDs, and additional users.
 * This class provides methods to update and retrieve the state information.
 *
 * @version 1.0
 * @since 2024-08-03
 */
public class SharedAccountSignupState {
    private String sharedAccountId;
    private String sharedPassword;
//    private String user1Id;
//    private String user2Id;
    private Set<String> userIds;
    private String stateError;
    private String successMsg;

    /**
     * Constructs a SharedAccountSignupState object with initial empty or null values.
     */
    public SharedAccountSignupState() {
        this.sharedAccountId = "";
        this.sharedPassword = "";
        this.userIds = new HashSet<>();
        this.stateError = null;
        this.successMsg = null;
    }

    /**
     * Gets the shared account ID.
     *
     * @return the shared account ID, or null if not set
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }

    /**
     * Sets the shared account ID.
     *
     * @param sharedAccountId the shared account ID to set
     */
    public void setSharedAccountId(String sharedAccountId) {
        this.sharedAccountId = sharedAccountId;
    }

    /**
     * Gets the shared account password.
     *
     * @return the shared account password, or null if not set
     */
    public String getSharedPassword() {
        return this.sharedPassword;
    }

    /**
     * Sets the shared account password.
     *
     * @param sharedPassword the shared account password to set
     */
    public void setSharedPassword(String sharedPassword) {
        this.sharedPassword = sharedPassword;
    }

    /**
     * Gets the ID of the first user.
     *
     * @return the ID of the first user, or null if not set
     */
    public Set<String> getUserId() {
        return this.userIds;
    }

//    /**
//     * Sets the ID of the first user.
//     *
//     * @param user1Id the ID of the first user to set
//     */
//    public void setUser1Id(String user1Id) {
//        this.user1Id = user1Id;
//    }

//    /**
//     * Gets the ID of the second user.
//     *
//     * @return the ID of the second user, or null if not set
//     */
//    public String getUser2Id() {
//        return user2Id;
//    }
//
//    /**
//     * Sets the ID of the second user.
//     *
//     * @param user2Id the ID of the second user to set
//     */
//    public void setUser2Id(String user2Id) {
//        this.user2Id = user2Id;
//    }

    public String getStateError() {
        return this.stateError;
    }

    public void setStateError(String stateError) {
        this.stateError = stateError;
    }

    public String getSuccessMsg() {
        return this.successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    /**
     * Adds a new user ID to the set of additional user IDs.
     *
     * @param userId the user ID to add
     */
    public void addUserId(String userId) {
        this.userIds.add(userId);
    }

    /**
     * Removes a user ID from the set of additional user IDs.
     *
     * @param userId the user ID to remove
     */
    public void removeUserId(String userId) {
        this.userIds.remove(userId);
    }


    /**
     * Sets the additional user IDs for the shared account.
     *
     * @param userIds the new set of additional user IDs
     */
    public void setUserIds(Set<String> userIds) {
        this.userIds = new HashSet<>(userIds);
    }

    /**
     * Clears all user IDs from the state.
     */
    public void clearUserIds() {
        this.userIds.clear();
    }

    /**
     * Checks if all required fields are filled out.
     *
     * @return true if all fields are filled, false otherwise
     */
    public boolean isComplete() {
        return this.successMsg != null;
    }

    /**
     * Resets the state to its initial empty values.
     */
    public void reset() {
        this.sharedAccountId = null;
        this.sharedPassword = null;
        this.userIds.clear();
    }
}

