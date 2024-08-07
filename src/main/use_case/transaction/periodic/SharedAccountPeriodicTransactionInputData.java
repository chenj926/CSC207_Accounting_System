package use_case.transaction.periodic;

import java.util.Set;

/**
 * The SharedAccountPeriodicTransactionInputData class represents the input data required for a periodic transaction operation
 * involving a shared account.
 * It includes details such as the shared account, transaction amount, start date, end date, period, description, category,
 * and the set of responsible users.
 */
public class SharedAccountPeriodicTransactionInputData extends PeriodicTransactionInputData {
    private final Set<String> responsibleUserIds; // Users responsible for the transaction

    /**
     * Constructs a SharedAccountPeriodicTransactionInputData object with the specified details.
     * @param transactionAmount      the amount of the transaction
     * @param transactionStartDate   the start date of the transaction
     * @param transactionEndDate     the end date of the transaction
     * @param transactionPeriod      the period of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     * @param responsibleUserIds     the set of user IDs responsible for the transaction
     */
    public SharedAccountPeriodicTransactionInputData(
            String transactionAmount,
            String transactionStartDate,
            String transactionEndDate,
            String transactionPeriod,
            String transactionDescription,
            String transactionCategory,
            Set<String> responsibleUserIds) {
        super(transactionAmount, transactionStartDate, transactionDescription, transactionPeriod, transactionEndDate, transactionCategory);
        this.responsibleUserIds = responsibleUserIds;
    }


    /**
     * Gets the set of user IDs responsible for the transaction.
     *
     * @return a set of user IDs responsible for the transaction
     */
    public Set<String> getResponsibleUserIds() {
        return responsibleUserIds;
    }
}
