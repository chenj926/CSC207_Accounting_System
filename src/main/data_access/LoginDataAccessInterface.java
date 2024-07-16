package data_access;

import entity.UserAccount;

public interface LoginDataAccessInterface {
    boolean existById(String identification);
    UserAccount getById(String identification);
    void login(UserAccount userAccount);
}