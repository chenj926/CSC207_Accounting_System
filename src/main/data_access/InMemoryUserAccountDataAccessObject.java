package data_access;

import entity.UserAccount;

import java.util.*;

// in memory DAO for test purposes
public class InMemoryUserAccountDataAccessObject implements UserSignupDataAccessInterface{
    private final Map<String, UserAccount> users = new HashMap<>();

    /**
     * @param identifier the user's identification
     * @return whether the user exists
     */
    @Override
    public boolean existById(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param newUser the data to save
     */
    public void save(UserAccount newUser) {
        users.put(newUser.getIdentification(), newUser);
    }

    public void deleteById(String identifier) {
        users.remove(identifier);
    }

    public UserAccount getById(String identifier) {
        return users.get(identifier);
    }

    public Map<String, UserAccount> getAllUserAcc() {
        return users;
    }
}