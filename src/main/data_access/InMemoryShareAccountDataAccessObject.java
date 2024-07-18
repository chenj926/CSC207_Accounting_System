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
        return this.shareAcc.containsKey(sharedAccountId);
    }

    /**
     * @param newSharedAcc the data to save
     */
    @Override
    public void save(SharedAccount newSharedAcc) {
        this.shareAcc.put(newSharedAcc.getIdentification(), newSharedAcc);
    }

    @Override
    public void update(SharedAccount sharedAcc) { this.shareAcc.put(sharedAcc.getIdentification(), sharedAcc); }

}