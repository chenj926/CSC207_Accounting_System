package use_case.homepage;

import data_access.account.AccountDataAccessInterface;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.Account;
import entity.account.UserAccount;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

public abstract class HomepageTwoInteractor<
        DAO extends AccountDataAccessInterface,
        PRE extends HomepageTwoOutBoundary<O>,
        I extends HomepageTwoInputData,
        O extends HomepageTwoOutputData> implements HomepageTwoInputBoundary<I> {

    protected final DAO userDataAccessObject;
    protected final PRE presenter;

    public HomepageTwoInteractor(DAO userDataAccessObject,
                                            PRE presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public abstract void execute(I inputData);
}
