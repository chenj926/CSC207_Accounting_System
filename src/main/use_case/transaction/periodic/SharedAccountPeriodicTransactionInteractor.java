package main.use_case.transaction.periodic;

import entity.account.UserAccount;
import data_access.account.UserAccountDataAccessInterface;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import use_case.transaction.TransactionInteractor;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.SharedAccountUserAccountPeriodicTransactionInputData;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputData;
//import use_case.transaction.periodic.SharedAccountPeriodicTransactionResponseModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    public void execute(SharedAccountUserAccountPeriodicTransactionInputData periodicTransactionInputData) {
        String identification = periodicTransactionInputData.getId();
        String stringAmount = periodicTransactionInputData.getTransactionAmount();
        String endDate = periodicTransactionInputData.getTransactionEndDate();
        String description = periodicTransactionInputData.getTransactionDescription();
        String startDate = periodicTransactionInputData.getTransactionStartDate();
        String period = periodicTransactionInputData.getTransactionPeriod();
        String category = periodicTransactionInputData.getTransactionCategory();
        Set<String> responsibleUserIds = periodicTransactionInputData.getResponsibleUserIds();

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
        processTransactions(isInflow, identification, amount, localStartDate, localEndDate, description, period, customPeriod, unit, category, responsibleUserIds);
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
     * @param identification     the shared account's identification
     * @param amount             the transaction amount
     * @param startDate          the transaction start date
     * @param endDate            the transaction end date
     * @param description        the transaction description
     * @param period             the transaction period
     * @param customPeriod       the custom period in days
     * @param unit               the ChronoUnit of the period
     * @param category           the transaction category
     * @param responsibleUserIds the set of user IDs responsible for the transaction
     */
    private void processTransactions(boolean isInflow, String identification, float amount, LocalDate startDate,
                                     LocalDate endDate, String description, String period, int customPeriod,
                                     ChronoUnit unit, String category, Set<String> responsibleUserIds) {
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (isInflow) {
                processInflowTransaction(identification, amount, currentDate, description, endDate, period, customPeriod, unit, category, responsibleUserIds);
            } else {
                processOutflowTransaction(identification, amount, currentDate, description, endDate, period, customPeriod, unit, category, responsibleUserIds);
            }

            // Update current date
            if (unit != ChronoUnit.DAYS) {
                currentDate = currentDate.plus(1, unit);
            } else if (customPeriod == 0) {
                currentDate = currentDate.plus(1, unit);
            } else {
                currentDate = currentDate.plusDays(customPeriod);
            }
        }
    }

    /**
     * Processes an inflow transaction for the shared account.
     * <p>
     * This method creates and saves a periodic inflow transaction, updates the
     * shared account's total income and balance, and interacts with the data access
     * object to save the transaction.
     * </p>
     *
     * @param identification      the shared account's identification
     * @param amount              the transaction amount
     * @param currentDate         the current transaction date
     * @param description         the transaction description
     * @param endDate             the transaction end date
     * @param period              the transaction period
     * @param customPeriod        the custom period in days
     * @param unit                the ChronoUnit of the period
     * @param category            the transaction category
     * @param responsibleUserIds  the set of user IDs responsible for the transaction
     */
    private void processInflowTransaction(String identification, float amount, LocalDate currentDate, String description,
                                          LocalDate endDate, String period, int customPeriod, ChronoUnit unit,
                                          String category, Set<String> responsibleUserIds) {
        PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, currentDate, description, endDate,
                period.equals("day") ? (int) unit.getDuration().toDays() : customPeriod, category);

        // Update the shared account's total income and balance
        float totalIncome = sharedAccount.getTotalIncome() + amount;
        sharedAccount.setTotalIncome(totalIncome);

        float totalBalance = sharedAccount.getTotalBalance() + amount;
        sharedAccount.setTotalBalance(totalBalance);

        // Save transaction
        SharedAccountPeriodicTransactionOutputData outputData = new SharedAccountPeriodicTransactionOutputData(
                periodicInflow, responsibleUserIds, sharedAccount.getTotalBalance());
        sharedAccountDataAccessInterface.saveSharedTransaction(null, outputData, true);
        sharedAccountDataAccessInterface.update(sharedAccount);
    }

    /**
     * Processes an outflow transaction for the shared account.
     * <p>
     * This method creates and saves a periodic outflow transaction, updates the
     * shared account's total outflow and balance, and interacts with the data access
     * object to save the transaction.
     * </p>
     *
     * @param identification      the shared account's identification
     * @param amount              the transaction amount
     * @param currentDate         the current transaction date
     * @param description         the transaction description
     * @param endDate             the transaction end date
     * @param period              the transaction period
     * @param customPeriod        the custom period in days
     * @param unit                the ChronoUnit of the period
     * @param category            the transaction category
     * @param responsibleUserIds  the set of user IDs responsible for the transaction
     */
    private void processOutflowTransaction(String identification, float amount, LocalDate currentDate, String description,
                                           LocalDate endDate, String period, int customPeriod, ChronoUnit unit,
                                           String category, Set<String> responsibleUserIds) {
        PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, currentDate, description, endDate,
                period.equals("day") ? (int) unit.getDuration().toDays() : customPeriod, category);

        // Update the shared account's total outflow and balance
        float totalOutflow = sharedAccount.getTotalOutflow() + amount;
        sharedAccount.setTotalOutflow(totalOutflow);

        float totalBalance = sharedAccount.getTotalBalance() - amount;
        sharedAccount.setTotalBalance(totalBalance);

        // Save transaction
        SharedAccountPeriodicTransactionOutputData outputData = new SharedAccountPeriodicTransactionOutputData(
                periodicOutflow, responsibleUserIds, sharedAccount.getTotalBalance());
        sharedAccountDataAccessInterface.saveSharedTransaction(null, outputData, false);
        sharedAccountDataAccessInterface.update(sharedAccount);
    }

    /**
     * Validates and parses the transaction period.
     * <p>
     * This method checks if the period is one of the predefined period types (day, week, month, year).
     * If not, it tries to parse the period as an integer representing a custom period in days.
     * If parsing fails or the custom period is 0, it returns -1 as an indication of failure.
     * </p>
     *
     * @param period the transaction period as a string
     * @return the custom period as an integer, or 0 if it's a predefined period, or -1 if parsing fails
     */
    private int validateAndParsePeriod(String period) {
        ArrayList<String> periodTypes = new ArrayList<>();
        periodTypes.add("day");
        periodTypes.add("week");
        periodTypes.add("month");
        periodTypes.add("year");

        if (periodTypes.contains(period)) {
            return 0;
        }

        try {
            int customPeriod = Integer.parseInt(period);
            if (customPeriod == 0) {
                return -1;
            }
            return customPeriod;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Returns the ChronoUnit corresponding to the given period string.
     * <p>
     * If the period is not one of the predefined types, it defaults to ChronoUnit.DAYS.
     * </p>
     *
     * @param period the transaction period as a string
     * @return the corresponding ChronoUnit
     */
    private ChronoUnit getChronoUnit(String period) {
        switch (period) {
            case "day":
                return ChronoUnit.DAYS;
            case "week":
                return ChronoUnit.WEEKS;
            case "month":
                return ChronoUnit.MONTHS;
            case "year":
                return ChronoUnit.YEARS;
            default:
                return ChronoUnit.DAYS;
        }
    }

    /**
     * Validates if the period is shorter than the duration between the start and end dates.
     *
     * @param unit the ChronoUnit of the period
     * @param customPeriod the custom period in days
     * @param startDate the start date
     * @param endDate the end date
     * @return true if the period is valid, false otherwise
     */
    private boolean validatePeriod(ChronoUnit unit, int customPeriod, LocalDate startDate, LocalDate endDate) {
        long totalDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // if the start day and end day is the same day
        if (totalDaysBetween == 0) {
            totalDaysBetween++;
        }

        if (unit != ChronoUnit.DAYS && totalDaysBetween < unit.getDuration().toDays()) {
            return false;
        } else if (totalDaysBetween < customPeriod) {
            return false;
        }

        return unit == ChronoUnit.DAYS || totalDaysBetween >= customPeriod;
    }
}






