package data_access;

import entity.UserAccount;

import java.util.*;

public class InMemoryOneTimeDataAccessObject implements UserAccountDataAccessInterface{
    private static Map<String, UserAccount> users;

    public InMemoryOneTimeDataAccessObject() {
        this.users = new HashMap<>();
    }

    @Override
    public boolean existById(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public UserAccount getById(String identification) {
        return null;
    }

    @Override
    public void update(UserAccount userAccount) {
        System.out.println("update");
    }

    @Override
    public void deleteById(String id) {
        System.out.println("id");
    }

    @Override
    public void save(UserAccount userAccount) {
        System.out.println("id");
    }

}
