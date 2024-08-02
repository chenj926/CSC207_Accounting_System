package use_case.transaction.one_time;

import entity.account.SharedAccount;
import entity.transaction.one_time.OneTimeTransaction;

import java.time.LocalDate;
import java.util.Set;

/**
 * The SharedAccountOneTimeTransactionInteractor class is responsible for handling one-time transactions
 * associated with a SharedAccount.
 *
 */
public class SharedAccountOneTimeTransactionInteractor {
    private final SharedAccount sharedAccount;

    /**
     * Constructs a SharedAccountOneTimeTransactionInteractor object with the specified SharedAccount.
     *
     * @param sharedAccount the SharedAccount to be used for one-time transactions
     */
    public SharedAccountOneTimeTransactionInteractor(SharedAccount sharedAccount) {
        this.sharedAccount = sharedAccount;
    }

    /**
     * Processes a one-time transaction for the SharedAccount.
     *
     * @param identification the identification of the one-time transaction
     * @param amount         the amount of the one-time transaction
     * @param date           the date of the one-time transaction
     * @param description    the description of the one-time transaction
     * @param category       the category of the one-time transaction
     * @param userIdentification the identification of the user performing the transaction
     * @return the created OneTimeTransaction object
     */
    public OneTimeTransaction processOneTimeTransaction(String identification, float amount, LocalDate date, String description, String category, String userIdentification) {
        Set<String> sharedUserIdentifications = sharedAccount.getSharedUserIdentifications();
        if (!sharedUserIdentifications.contains(userIdentification)) {
            throw new IllegalArgumentException("User identification is not associated with the SharedAccount.");
        }

        // Create the OneTimeTransaction object
        OneTimeTransaction transaction = new OneTimeTransaction(identification, amount, date, description, category);

        // Update the SharedAccount balance
        sharedAccount.updateBalance(transaction.getAmount());

        return transaction;
    }
}