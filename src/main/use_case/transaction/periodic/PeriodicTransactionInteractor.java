package use_case.transaction.periodic;

import data_access.account.AccountDataAccessInterface;
import entity.account.Account;
import use_case.transaction.one_time.OneTimeTransactionOutputData;

public abstract class PeriodicTransactionInteractor <
        DAO extends AccountDataAccessInterface<A, O, P>,
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P extends PeriodicTransactionOutputData> {
}
