package data_access;

import entity.UserAccount;
import use_case.OneTimeTransactionOutputData;
import use_case.PeriodicTransactionOutputData;

public interface UserAccountDataAccessInterface {
    boolean existById(String identification);
    UserAccount getById(String identification);
    void update(UserAccount userAccount);
    void deleteById(String identifier);
    void save(UserAccount suerAccount);
    void saveTransaction(OneTimeTransactionOutputData outputData,
                         PeriodicTransactionOutputData periodicTransactionOutputData, boolean isPeriodic);


    // void updateId(UserAccount userAccount);
    //account balance
//    void updateBalance(float balance);
//    //account inflow
//    void updateInflow(float inFlow);
//    //account outflow
//    void updateOutflow(float outFlow);

}
