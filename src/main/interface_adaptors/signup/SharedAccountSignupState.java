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
public class SharedAccountSignupState extends SignupState {
    private Set<String> userIds;

    /**
     * Constructs a SharedAccountSignupState object with initial empty or null values.
     */
    public SharedAccountSignupState() {
        super();
        this.userIds = new HashSet<>();
    }

    /**
     * Gets the ID of the first user.
     *
     * @return the ID of the first user, or null if not set
     */
    public Set<String> getUserId() {
        return this.userIds;
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
        super.reset();
        this.userIds.clear();
    }
}

