package use_case.update_periodic_at_login;

import data_access.account.AccountDataAccessInterface;
import entity.account.Account;
import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AccountUpdatePeriodicAtLoginInteractor<
        DataAccessInterface extends AccountDataAccessInterface,
        A extends Account,
        O extends PeriodicTransactionOutputData> {
    protected final DataAccessInterface dataAccessObject;

    /**
     * Constructs an UserAccountUpdatePeriodicAtLoginInteractor with the given UserAccountDataAccessInterface.
     *
     * @param dataAccessObject the data access object for user accounts
     */
    public AccountUpdatePeriodicAtLoginInteractor(DataAccessInterface dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }


    protected Map<String, PeriodicTransaction> getLatestTransactionsMap(String id, LocalDate lastLoginDate) {
        List<Transaction> transactions = dataAccessObject.readTransactions(id);
        Map<String, PeriodicTransaction> latestTransactionsMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction instanceof PeriodicTransaction) {
                PeriodicTransaction periodicTransaction = (PeriodicTransaction) transaction;
                String uniqueKey = getUniqueKey(periodicTransaction);

                // Ensure we are considering transactions strictly before the last login date
                if (periodicTransaction.getDate().isBefore(lastLoginDate)) {
                    if (!latestTransactionsMap.containsKey(uniqueKey) || periodicTransaction.getDate().isAfter(latestTransactionsMap.get(uniqueKey).getDate())) {
                        latestTransactionsMap.put(uniqueKey, periodicTransaction);
                    }
                }
            }
        }

        return latestTransactionsMap;
    }

    protected String getUniqueKey(PeriodicTransaction periodicTransaction) {
        return periodicTransaction.getIdentification() + "|" +
                periodicTransaction.getTransactionCategory() + "|" +
                periodicTransaction.getDescription() + "|" +
                periodicTransaction.getAmount() + "|" +
                periodicTransaction.getStartDate().toString() + "|" +
                periodicTransaction.getEndDate().toString() + "|" +
                periodicTransaction.getPeriod() + "|" +
                (periodicTransaction.getAmount() >= 0 ? "inflow" : "outflow");
    }

    protected void processTransaction(A account, PeriodicTransaction periodicTransaction, LocalDate currentDate) {
        LocalDate endDate = periodicTransaction.getEndDate();
        LocalDate lastRecordedDate = periodicTransaction.getDate();
        LocalDate date = lastRecordedDate.plusDays(1); // start from the day after the last recorded date
        String period = periodicTransaction.getPeriod();

        // Get the corresponding ChronoUnit
        ChronoUnit unit = getChronoUnit(period);
        int customPeriod = validateAndParsePeriod(period);

        // Ensure we do not go beyond currentDate or endDate
        while (!date.isAfter(currentDate) && !date.isAfter(endDate)) {
            if (periodicTransaction.getAmount() >= 0) {
                processInflow(account, periodicTransaction, dataAccessObject, date);
            } else {
                processOutflow(account, periodicTransaction, dataAccessObject, date);
            }

            // Update date
            date = getNextDate(date, unit, customPeriod);
        }
    }

    protected LocalDate getNextDate(LocalDate date, ChronoUnit unit, int customPeriod) {
        if (unit != ChronoUnit.DAYS) {
            return date.plus(1, unit);
        } else if (customPeriod == 0) {
            return date.plus(1, ChronoUnit.DAYS);
        } else {
            return date.plusDays(customPeriod);
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
    protected ChronoUnit getChronoUnit(String period) {
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
     * Validates and parses the period string. If the period is a predefined type, it returns 0.
     * Otherwise, it parses the custom period as an integer.
     *
     * @param period the transaction period as a string
     * @return the parsed custom period or 0 if the period is predefined
     */
    protected int validateAndParsePeriod(String period) {
        ArrayList<String> periodTypes = new ArrayList<>();
        periodTypes.add("day");
        periodTypes.add("week");
        periodTypes.add("month");
        periodTypes.add("year");

        if (periodTypes.contains(period)) {
            return 0;
        }

        // the period is prechecked in interactor and stored, so no need to check again
        return Integer.parseInt(period);
    }

    /**
     * Processes an inflow transaction by creating a new PeriodicInflow object, updating the user's total income
     * and balance, and saving the transaction through the data access object.
     *
     * @param account             the user account
     * @param periodicTransaction the periodic transaction
     * @param dataAccessObject    the data access object for user accounts
     * @param date                the date of the transaction
     */
    protected abstract void processInflow(A account, PeriodicTransaction periodicTransaction, DataAccessInterface dataAccessObject, LocalDate date);

    /**
     * Processes an outflow transaction by creating a new PeriodicOutflow object, updating the user's total outflow
     * and balance, and saving the transaction through the data access object.
     *
     * @param userAccount          the user account
     * @param periodicTransaction  the periodic transaction
     * @param userDataAccessObject the data access object for user accounts
     * @param date                 the date of the transaction
     */
    protected abstract void processOutflow(A userAccount, PeriodicTransaction periodicTransaction, DataAccessInterface userDataAccessObject, LocalDate date);
}