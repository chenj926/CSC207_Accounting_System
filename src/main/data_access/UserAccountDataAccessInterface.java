package data_access;

import entity.UserAccount;

public interface UserAccountDataAccessInterface {
    boolean existById(String identification);
    UserAccount getById(String identification);

    void getId(String identification);

    void updateId(UserAccount userAccount);
    //account balance
    void updateBalance(UserAccount userAccount);
    //account inflow
    void updateInflow(UserAccount userAccount);
    //account outflow
    void updateOutflow(UserAccount userAccount);

}
