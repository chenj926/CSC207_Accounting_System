package data_access;

import entity.AccountFactory;

public interface UserSignupDataAccessInterface {
    boolean existById(String identification);

    void save(AccountFactory user);
}