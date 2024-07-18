package data_access;

import data_access.ShareAccountDataAccessInterface;
import entity.SharedAccount;

import java.util.*;

// in memory DAO for test purposes
public class InMemoryShareAccountDataAccessObject implements ShareAccountDataAccessInterface{
    private final Map<String, SharedAccount> shareAcc = new HashMap<>();

    /**
     * @param sharedAccountId the user's identification
     * @return whether the user exists
     */
    @Override
    public boolean existById(String sharedAccountId) {
        return shareAcc.containsKey(sharedAccountId);
    }

    /**
     * @param newSharedAcc the data to save
     */
    public void save(SharedAccount newSharedAcc) {
        shareAcc.put(newSharedAcc.getIdentification(), newSharedAcc);
    }

    public void deleteById(String sharedAccountId) {
        shareAcc.remove(sharedAccountId);
    }

    public SharedAccount getById(String sharedAccountId) {
        return shareAcc.get(sharedAccountId);
    }

    public Map<String, SharedAccount> getAllShareAcc() {
        return shareAcc;
    }
}