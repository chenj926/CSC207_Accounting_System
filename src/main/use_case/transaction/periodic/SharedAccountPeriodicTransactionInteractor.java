package use_case.transaction.periodic;

import data_access.account.SharedAccountDataAccessInterface;
import entity.account.SharedAccount;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputData;
//import use_case.transaction.periodic.SharedAccountPeriodicTransactionResponseModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The SharedAccountPeriodicTransactionInteractor class is responsible for managing periodic transactions
 * for shared accounts. It implements the SharedAccountPeriodicTransactionInputBoundary and
 * SharedAccountPeriodicTransactionOutputBoundary interfaces.
 *
 * @author Rita
 */
public class SharedAccountPeriodicTransactionInteractor extends PeriodicTransactionInteractor <
        SharedAccountDataAccessInterface,
        SharedAccount,
        SharedAccountOneTimeTransactionOutputData,
        SharedAccountPeriodicTransactionOutputData,
        SharedAccountPeriodicTransactionInputData>
        implements SharedAccountPeriodicTransactionInputBoundary {

//    private final SharedAccountPeriodicTransactionOutputBoundary outputBoundary;

    /**
     * Constructs a SharedAccountPeriodicTransactionInteractor object with the necessary dependencies.
     *
     * @param DAO the data access interface for user account data
     * @param sharedAccount                   the user account associated with the periodic transaction
     * @param presenter  the output boundary for the periodic transaction use case
     */
    public SharedAccountPeriodicTransactionInteractor(SharedAccountDataAccessInterface DAO,
                                                      SharedAccount sharedAccount,
                                                      SharedAccountPeriodicTransactionOutputBoundary presenter) {
        super(DAO, presenter, sharedAccount);
//        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SharedAccountPeriodicTransactionInputData periodicTransactionInputData) {
        String userId = periodicTransactionInputData.getId();
        String sharedId = periodicTransactionInputData.getSharedAccountId();
        String stringAmount = periodicTransactionInputData.getTransactionAmount();
        String endDate = periodicTransactionInputData.getTransactionEndDate();
        String description = periodicTransactionInputData.getTransactionDescription();
        String startDate = periodicTransactionInputData.getTransactionStartDate();
        String period = periodicTransactionInputData.getTransactionPeriod();
        String category = periodicTransactionInputData.getTransactionCategory();

        //Set currentDate to today
        LocalDate currentDate = LocalDate.now();

        // Validate input
        if (!checkValid(stringAmount) || !checkValid(startDate) || !checkValid(endDate) ||
                !checkValid(description) || !checkValid(period)) {
            System.out.println("Please do NOT have any empty inputs!");
            return;
        }

        // Parse and validate amount
        float amount = parseAmount(stringAmount);
        if (amount == Float.MIN_VALUE) {
            System.out.println("Incorrect amount! Please ONLY enter number");
            return;
        }

        // Parse and validate dates
        LocalDate localStartDate = parseDate(startDate);
        LocalDate localEndDate = parseDate(endDate);
        if (localStartDate == null || localEndDate == null || localStartDate.isAfter(localEndDate)) {
            System.out.println("Invalid date format or start date after end date! Please enter again!");
            return;
        }

        // Validate and parse the period
        int customPeriod = validateAndParsePeriod(period);
        if (customPeriod == -1) {
            System.out.println("The custom period is not in correct format or is 0, please enter again!");
            return;
        }

        // Determine the ChronoUnit for the period
        ChronoUnit unit = getChronoUnit(period);
        if (!validatePeriod(unit, customPeriod, localStartDate, localEndDate)) {
            System.out.println("Period is longer than the period between start and end date!");
            return;
        }

        boolean isInflow = amount >= 0.0; // Determine if it's an inflow
        processTransactions(isInflow, userId, sharedId, amount, localStartDate, localEndDate, description, period,
                customPeriod, unit, category, currentDate);
    }

    /**
     * Processes the transactions based on whether they are inflow or outflow.
     * <p>
     * This method iterates over the period between the start and end dates,
     * creating and saving inflow or outflow transactions at each step. It updates
     * the shared account's balance and interacts with the data access object to
     * save the transactions.
     * </p>
     *
     * @param isInflow           boolean indicating if the transaction is an inflow
     * @param userId             the user account's id
     * @param shareId            the shared account's id
     * @param amount             the transaction amount
     * @param startDate          the transaction start date
     * @param endDate            the transaction end date
     * @param description        the transaction description
     * @param period             the transaction period
     * @param customPeriod       the custom period in days
     * @param unit               the ChronoUnit of the period
     * @param category           the transaction category
     * @param currentDate the set of user IDs responsible for the transaction
     */
    private void processTransactions(boolean isInflow, String userId, String shareId, float amount, LocalDate startDate,
                                     LocalDate endDate, String description, String period, int customPeriod,
                                     ChronoUnit unit, String category, LocalDate currentDate) {
        LocalDate date = startDate;
        String userIds = shareId + ";" + userId;

        SharedAccountPeriodicTransactionOutputData finalOutputData = null;

        while (!date.isAfter(currentDate) && !date.isAfter(endDate)) {
            if (isInflow) {
                finalOutputData = this.processInflowTransaction(userIds, amount, startDate, description, endDate,
                        period, category, date);
            } else {
                finalOutputData = this.processOutflowTransaction(userIds, amount, startDate, description, endDate,
                        period, category, date);

                // Update current date
                if (unit != ChronoUnit.DAYS) {
                    currentDate = currentDate.plus(1, unit);
                } else if (customPeriod == 0) {
                    currentDate = currentDate.plus(1, unit);
                } else {
                    currentDate = currentDate.plusDays(customPeriod);
                }
            }

            // update the success view only after all transactions are done
            if (finalOutputData != null) {
                this.presenter.prepareSuccessView(finalOutputData);
            }
        }
    }

    @Override
    protected SharedAccountPeriodicTransactionOutputData createOutputData(PeriodicTransaction transaction) {
        return new SharedAccountPeriodicTransactionOutputData(transaction);
    }
}






