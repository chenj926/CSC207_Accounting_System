package data_access;

import entity.UserAccount;

public interface UserAccountDataAccessInterface {
    boolean existById(String identification);
    UserAccount getById(String identification);

    // void updateId(UserAccount userAccount);
    //account balance
//    void updateBalance(float balance);
//    //account inflow
//    void updateInflow(float inFlow);
//    //account outflow
//    void updateOutflow(float outFlow);

}
