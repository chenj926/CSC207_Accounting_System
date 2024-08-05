package use_case.transaction.periodic;

import entity.account.SharedAccount;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionOutputData;

import java.time.LocalDate;

/**
 * The SharedAccountPeriodicTransactionOutputData class represents the output data of a periodic transaction operation
 * involving a shared account.
 */
public class SharedAccountPeriodicTransactionOutputData extends PeriodicTransactionOutputData {
    private SharedAccount sharedAccount;

    /**
     * Constructs a SharedAccountPeriodicTransactionOutputData object with the specified details.
     *
     * @param periodicTransaction the periodic transaction entity
     * @param sharedAccount       the shared account for the transaction
     */
    public SharedAccountPeriodicTransactionOutputData(PeriodicTransaction periodicTransaction, SharedAccount sharedAccount) {
        super(periodicTransaction);
        this.sharedAccount = sharedAccount;
    }

    /**
     * Gets the shared account for the transaction.
     *
     * @return the shared account for the transaction
     */
    public SharedAccount getSharedAccount() {
        return this.sharedAccount;
    }

    /**
     * Sets the shared account for the transaction.
     *
     * @param sharedAccount the new shared account for the transaction
     */
    public void setSharedAccount(SharedAccount sharedAccount) {
        this.sharedAccount = sharedAccount;
    }
}