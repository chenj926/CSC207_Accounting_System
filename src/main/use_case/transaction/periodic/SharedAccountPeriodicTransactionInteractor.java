package main.use_case.transaction.periodic;

import entity.account.SharedAccount;
import entity.account.UserAccount;
import data_access.account.UserAccountDataAccessInterface;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionInteractor;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionResponseModel;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * The SharedAccountPeriodicTransactionInteractor class is responsible for managing periodic transactions
 * for shared accounts. It implements the SharedAccountPeriodicTransactionInputBoundary and
 * SharedAccountPeriodicTransactionOutputBoundary interfaces.
 *
 * @author Rita
 */
public class SharedAccountPeriodicTransactionInteractor extends TransactionInteractor
        implements SharedAccountPeriodicTransactionInputBoundary {

    private final SharedAccountPeriodicTransactionOutputBoundary outputBoundary;

    /**
     * Constructs a SharedAccountPeriodicTransactionInteractor object with the necessary dependencies.
     *
     * @param userAccountDataAccessInterface the data access interface for user account data
     * @param userAccount                   the user account associated with the periodic transaction
     * @param outputBoundary                the output boundary for the periodic transaction use case
     */
    public SharedAccountPeriodicTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                                      UserAccount userAccount,
                                                      SharedAccountPeriodicTransactionOutputBoundary outputBoundary) {
        super(userAccountDataAccessInterface, userAccount);
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void createPeriodicTransaction(String amount, String startDate, String endDate, String description,
                                          Set<String> sharedUserIdentifications) {
        // Validate user input
        if (!checkValid(amount) || !checkValid(startDate) || !checkValid(endDate) || !checkValid(description)) {
            outputBoundary.prepareFailureView("Invalid input provided.");
            return;
        }

        float transactionAmount = parseAmount(amount);
        if (transactionAmount == Float.MIN_VALUE) {
            outputBoundary.prepareFailureView("Invalid transaction amount.");
            return;
        }

        LocalDate start = parseDate(startDate);
        LocalDate end = parseDate(endDate);
        if (start == null || end == null) {
            outputBoundary.prepareFailureView("Invalid start or end date.");
            return;
        }

        // Create the periodic transaction
        PeriodicTransaction periodicTransaction = new PeriodicTransaction(transactionAmount, start, end, description);

        // Create the shared account and associate the periodic transaction
        SharedAccount sharedAccount = (SharedAccount) userAccount;
        sharedAccount.addPeriodicTransaction(periodicTransaction);

        // Add shared user identifications to the shared account
        Set<String> updatedSharedUserIdentifications = new HashSet<>(sharedAccount.getSharedUserIdentifications());
        updatedSharedUserIdentifications.addAll(sharedUserIdentifications);
        sharedAccount.setSharedUserIdentifications(updatedSharedUserIdentifications);

        // Prepare the response model and send it to the output boundary
        SharedAccountPeriodicTransactionResponseModel responseModel = new SharedAccountPeriodicTransactionResponseModel(
                sharedAccount.getIdentification(), periodicTransaction, updatedSharedUserIdentifications);
        outputBoundary.prepareSuccessView(responseModel);
    }
}