package entity.account;

import java.util.HashSet;
import java.util.Set;

/**
 * The SharedAccount class extends the UserAccount class to represent a shared account.
 * It includes additional functionality to manage user identifications associated with the shared account.
 *
 * @author Rita
 * @author Jessica
 * @author Eric
 */
public class SharedAccount extends UserAccount {
    private Set<String> sharedUserIdentifications;
    private String sharedAccountPassword;

    /**
     * Constructs a SharedAccount object with the specified shared account identification.
     * Initializes an empty set of shared user identifications.
     *
     * @param shareAccountIdentification the identification for the shared account
     */
    public SharedAccount(String shareAccountIdentification, String sharedAccountPassword) {
        super(null, null, shareAccountIdentification); // SharedAccount have own shared account id
        this.sharedUserIdentifications = new HashSet<>();
        this.sharedAccountPassword = sharedAccountPassword;
    }

    /**
     * Gets the set of shared user identifications associated with this shared account.
     *
     * @return a set of shared user identifications
     */
    public Set<String> getSharedUserIdentifications() {
        return this.sharedUserIdentifications;
    }

    /**
     * Adds a user identification to the set of shared user identifications.
     *
     * @param identification the user identification to be added
     */
    public void addUserIdentification(String identification) {
        sharedUserIdentifications.add(identification);
    }

    /**
     * Removes a user identification from the set of shared user identifications.
     *
     * @param identification the user identification to be removed
     */
    public void removeUserIdentification(String identification) {
        sharedUserIdentifications.remove(identification);
    }

    /**
     * Sets the shared user identifications for this shared account.
     *
     * @param sharedUserIdentifications the new set of shared user identifications
     */
    public void setSharedUserIdentifications(Set<String> sharedUserIdentifications) {
        this.sharedUserIdentifications = sharedUserIdentifications;
    }

    /**
     * Gets the shared password for this shared account.
     *
     * @return the shared account password
     */
    public String getSharedAccountPassword() {
        return this.sharedAccountPassword;
    }

    /**
     * Sets a new shared password for this shared account.
     *
     * @param sharedAccountPassword the new password for the shared account
     */
    public void setSharedAccountPassword(String sharedAccountPassword) {
        this.sharedAccountPassword = sharedAccountPassword;
    }

}