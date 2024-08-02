package use_case.transaction.periodic;

import data_access.account.UserAccountDataAccessInterface;

import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.account.UserAccount;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

/**
 * The PeriodicTransactionInteractor class implements the PeriodicTransactionInputBoundary interface.
 * It handles the process of creating a periodic transaction by validating the input data,
 * interacting with the data access layer, and using the presenter to prepare the output views.
 *
 * @author Eric
 * @author Dana
 */
public class PeriodicTransactionInteractor implements PeriodicTransactionInputBoundary {
    final UserAccountDataAccessInterface userDataAccessObject;
    final PeriodicTransactionOutputBoundary presenter;
    final UserAccount userAccount;

    /**
     * Constructs a PeriodicTransactionInteractor object with the specified data access interface,
     * output boundary, and user account.
     *
     * @param userAccountDataAccessInterface the data access interface for user data
     * @param periodicTransactionOutputBoundary the output boundary for presenting the periodic transaction results
     * @param userAccount the user account associated with the transaction
     */
    public PeriodicTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                         PeriodicTransactionOutputBoundary periodicTransactionOutputBoundary,
                                         UserAccount userAccount) {
        this.userDataAccessObject = userAccountDataAccessInterface;
        this.presenter = periodicTransactionOutputBoundary;
        this.userAccount = userAccount;
    }

    /**
     * Executes the periodic transaction process with the given input data.
     *
     * @param periodicTransactionInputData the input data required for the periodic transaction process
     */
    @Override
    public void execute(PeriodicTransactionInputData periodicTransactionInputData) {
        String identification = userAccount.getIdentification();
        String stringAmount = periodicTransactionInputData.getTransactionAmount();
        String endDate = periodicTransactionInputData.getTransactionEndDate();
        String description = periodicTransactionInputData.getTransactionDescription();
        String startDate = periodicTransactionInputData.getTransactionStartDate();
        String period = periodicTransactionInputData.getTransactionPeriod();
        String category = periodicTransactionInputData.getTransactionCategory();

        // if user entered empty input in one or more of the input fields
        if(!checkValid(stringAmount) || !checkValid(startDate) || !checkValid(endDate) ||
                !checkValid(description) || !checkValid(period)) {
            presenter.prepareFailView("Please do NOT have any empty inputs!");
            return;
        }

        // Parse and validate the amount
        float amount = parseAmount(stringAmount);
        // we set float.MIN VAL to be the false output of the helper
        if (amount == Float.MIN_VALUE) {
            presenter.prepareFailView("Incorrect amount! please ONLY enter number");
            return;
        }

        // Parse and validate the dates
        LocalDate localStartDate = parseDate(startDate);
        LocalDate localEndDate = parseDate(endDate);
        // null is returned or period is longer than the days between start and end
        if (localStartDate == null || localEndDate == null || localStartDate.isAfter(localEndDate)) {
            presenter.prepareFailView("Invalid date format or start date after end date! Please enter again!");
            return;
        }

        // Validate and parse the period
        int customPeriod = validateAndParsePeriod(period);
        if (customPeriod == -1) {
            presenter.prepareFailView("The custom period is not in correct format or is 0, please enter again!");
            return;
        }

        // Determine the ChronoUnit for the period
        ChronoUnit unit = getChronoUnit(period);
        if (!validatePeriod(unit, customPeriod, localStartDate, localEndDate)) {
            presenter.prepareFailView("Period is longer than the period between start and end date!");
            return;
        }

        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false
        processTransactions(isInflow, identification, amount, localStartDate, localEndDate, description, period, customPeriod, unit, category);
    }

    /**
     * Checks if the provided user input is valid (not null or empty).
     *
     * @param userInfo the user input to check
     * @return true if the user input is valid, false otherwise
     */
    public boolean checkValid(String userInfo) {
        if (userInfo == null || userInfo.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Parses and formats the transaction amount to two decimal places.
     * <p>
     * This method tries to parse the input string to a float and formats it to two decimal places.
     * If the parsing fails, it returns Float.MIN_VALUE as an indication of failure.
     * </p>
     *
     * @param stringAmount the transaction amount as a string
     * @return the parsed and formatted amount as a float, or Float.MIN_VALUE if parsing fails
     */
    private float parseAmount(String stringAmount) {
        try {
            float amount = Float.parseFloat(stringAmount);
            return Float.parseFloat(String.format("%.2f", amount));
        } catch (NumberFormatException e) {
            return Float.MIN_VALUE;
        }
    }

    /**
     * Parses and validates the transaction date.
     * <p>
     * This method tries to parse the input date string to a LocalDate object using a strict date format.
     * If the parsing fails, it returns null as an indication of failure.
     * </p>
     *
     * @param date the transaction date as a string
     * @return the parsed date as a LocalDate object, or null if parsing fails
     */
    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
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

    /**
     * Processes the transactions based on whether they are inflow or outflow.
     * <p>
     * This method iterates over the period between the start and end dates, creating and saving
     * inflow or outflow transactions at each step. It updates the user account's balance and
     * interacts with the data access object to save the transactions.
     * </p>
     *
     * @param isInflow boolean indicating if the transaction is an inflow
     * @param identification the user's identification
     * @param amount the transaction amount
     * @param startDate the transaction start date
     * @param endDate the transaction end date
     * @param description the transaction description
     * @param period the transaction period
     * @param customPeriod the custom period in days
     * @param unit the ChronoUnit of the period
     */
    private void processTransactions(boolean isInflow, String identification, float amount, LocalDate startDate,
                                     LocalDate endDate, String description, String period, int customPeriod,
                                     ChronoUnit unit, String category) {
        LocalDate currentDate = startDate;
        PeriodicTransactionOutputData finalOutputData = null;

        while (!currentDate.isAfter(endDate)) {
            if (isInflow) {
                finalOutputData = processInflowTransaction(identification, amount, currentDate, description, endDate,
                        period, customPeriod, unit, category);
            } else {
                finalOutputData = processOutflowTransaction(identification, amount, currentDate, description, endDate,
                        period, customPeriod, unit, category);
            }

            // update current date
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
            presenter.prepareSuccessView(finalOutputData);
        }
    }

    /**
     * Processes an inflow transaction.
     * <p>
     * This method creates and saves a periodic inflow transaction, updates the user's total income
     * and balance, and interacts with the data access object to save the transaction.
     * </p>
     *
     * @param identification the user's identification
     * @param amount the transaction amount
     * @param currentDate the current transaction date
     * @param description the transaction description
     * @param endDate the transaction end date
     * @param period the transaction period
     * @param customPeriod the custom period in days
     * @param unit the ChronoUnit of the period
     */
    private PeriodicTransactionOutputData  processInflowTransaction(String identification, float amount, LocalDate currentDate, String description,
                                          LocalDate endDate, String period, int customPeriod, ChronoUnit unit, String category) {
        PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, currentDate, description, endDate,
                period.equals("day") ? (int) unit.getDuration().toDays() : customPeriod, category);
        // ?: if true (int) it, false, remain it as custom period

        // Create a new PeriodicInflow object
        float totalIncome = userAccount.getTotalIncome() + amount;
        userAccount.setTotalIncome(totalIncome);

        // Update the user's total income and balance
        float totalBalance = userAccount.getTotalBalance() + amount;
        userAccount.setTotalBalance(totalBalance);

        // Prepare the output data
        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicInflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(userAccount);

        return outputData;
    }

    /**
     * Processes an outflow transaction.
     * <p>
     * This method creates and saves a periodic outflow transaction, updates the user's total outflow
     * and balance, and interacts with the data access object to save the transaction.
     * </p>
     *
     * @param identification the user's identification
     * @param amount the transaction amount
     * @param currentDate the current transaction date
     * @param description the transaction description
     * @param endDate the transaction end date
     * @param period the transaction period
     * @param customPeriod the custom period in days
     * @param unit the ChronoUnit of the period
     */
    private PeriodicTransactionOutputData  processOutflowTransaction(String identification, float amount, LocalDate currentDate, String description,
                                           LocalDate endDate, String period, int customPeriod, ChronoUnit unit, String category) {
        // Create a new PeriodicOutflow object
        PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, currentDate, description, endDate,
                period.equals("day") ? (int) unit.getDuration().toDays() : customPeriod, category);
        // ?: if true (int) it, false, remain it as custom period

        // Update the user's total outflow and balance
        float totalOutflow = userAccount.getTotalOutflow() + amount;
        userAccount.setTotalOutflow(totalOutflow);

        float totalBalance = userAccount.getTotalBalance() + amount;
        userAccount.setTotalBalance(totalBalance);

        // Prepare the output data
        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicOutflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(userAccount);

        return outputData;
    }
}