package data_access;

import entity.UserAccount;

public interface ShareAccountDataAccessInterface {
    boolean existById(String identification);

    void save(UserAccount users);
}