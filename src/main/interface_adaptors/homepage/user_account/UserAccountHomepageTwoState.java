package interface_adaptors.homepage.user_account;

import interface_adaptors.homepage.HomepageTwoState;

public class UserAccountHomepageTwoState extends HomepageTwoState {
    private String username;

    // getters
    public String getUsername() {
        return this.username;
    }

    // setters
    public void setUsername(String username) {
        this.username = username;
    }
}

