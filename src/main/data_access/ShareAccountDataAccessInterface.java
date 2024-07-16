package data_access;

import entity.SharedAccount;
import entity.UserAccount;

public interface ShareAccountDataAccessInterface {
    boolean existById(String identification);

    void save(SharedAccount sharedAccount);
}