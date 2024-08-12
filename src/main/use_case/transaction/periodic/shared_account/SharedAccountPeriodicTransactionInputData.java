package use_case.transaction.periodic.shared_account;

import use_case.transaction.periodic.PeriodicTransactionInputData;

/**
 * The SharedAccountUserAccountPeriodicTransactionInputData class represents the input data required for a periodic transaction operation
 * involving a shared account.
 * It includes details such as the shared account, transaction amount, start date, end date, period, description, category,
 * and the set of responsible users.
 */
public class SharedAccountPeriodicTransactionInputData extends PeriodicTransactionInputData {
//    private final Set<String> responsibleUserIds; // Users responsible for the transaction
    private final String sharedAccountId;


    public SharedAccountPeriodicTransactionInputData(
            String transactionAmount,
            String transactionStartDate,
            String transactionEndDate,
            String transactionPeriod,
            String transactionDescription,
            String transactionCategory,
            String transactionDate,
            String sharedAccountId,
            String userId) {
        super(userId, transactionAmount, transactionStartDate, transactionDescription, transactionPeriod, transactionEndDate, transactionCategory, transactionDate);
        this.sharedAccountId = sharedAccountId;
    }


    /**
     * Gets the set of user IDs responsible for the transaction.
     *
     * @return a set of user IDs responsible for the transaction
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }

    // public Set<String> getResponsibleUserIds();

}
