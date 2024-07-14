package data_access;

import entity.UserAccount;

public interface UserSignupDataAccessInterface {
    boolean existById(String identification);

    void save(UserAccount user);
}