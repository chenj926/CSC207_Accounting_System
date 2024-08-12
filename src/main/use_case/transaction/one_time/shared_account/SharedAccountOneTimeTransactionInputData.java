package use_case.transaction.one_time.shared_account;

import use_case.transaction.one_time.OneTimeTransactionInputData;

/**
 * The SharedAccountOneTimeTransactionInputData class represents the input data required for a one-time transaction operation
 * that involves a shared account.
 * It includes details such as the transaction amount, date, description, category, the shared account identifier,
 * and the set of users responsible for the transaction.
 */
public class SharedAccountOneTimeTransactionInputData extends OneTimeTransactionInputData {
    private final String sharedAccountId;
//    private final Set<String> userIds; // Changed from Map to Set to reflect user responsibilities

    /**
     * Constructs a SharedAccountOneTimeTransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionDate        the date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     * @param sharedAccountId        the identifier of the shared account
     * @param userId    a set of user IDs responsible for the transaction
     */
    public SharedAccountOneTimeTransactionInputData(
            String transactionAmount,
            String transactionDate,
            String transactionDescription,
            String transactionCategory,
            String sharedAccountId,
            String userId) {
        super(userId, transactionAmount, transactionDescription, transactionCategory, transactionDate);
//        this.userIds = userIds;
        this.sharedAccountId = sharedAccountId;
        // 需要user输入自己的id
        // DAO 也要随之而改了
    }

    /**
     * Gets the identifier of the shared account.
     *
     * @return the identifier of the shared account
     */
    public String getSharedAccountId() {
        return sharedAccountId;
    }

    /**
     * Gets the set of user IDs responsible for the transaction.
     *
     * @return a set of user IDs responsible for the transaction
     */
//    public Set<String> getResponsibleUserIds() {
//        return this.userIds;
//    }
}

