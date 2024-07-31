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

        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false

        float income = 0.0f;
        float outFlow = 0.0f;

        // list of period types
        ArrayList<String> periodTypes = createPeriodTypes();

        int customPeriod = 0;

        // update the inflow and outflow
        // inflow
        if (isInflow) {
            LocalDate localStartDate = parseDate(startDate);
            LocalDate localEndDate = parseDate(endDate);

            // if any of the start end has issue
            if (localStartDate == null) {
                presenter.prepareFailView("Invalid date format for start date! Please enter again.");
                return;
            }
            if (localEndDate == null) {
                presenter.prepareFailView("Invalid date format for end date! Please enter again.");
                return;
            }
            if (localStartDate.isAfter(localEndDate)) {
                presenter.prepareFailView("Start date after end day, Plz enter again");
                return;
            }

            // if the user did not choose the pre-determined period
            if (!periodTypes.contains(period)) {
                try {
                    // assume the custom period is in days
                    customPeriod = Integer.parseInt(period);
                } catch (NumberFormatException e) {
                    presenter.prepareFailView("The custom period is not in correct format, please enter again!");
                    return;  // fail then do not excute the rest of the code
                }
                if (Integer.parseInt(period) == 0) {
                    presenter.prepareFailView("The custom period can not be 0, please enter again!");
                    return;  // custom days can not be 0
                }
            }

            ChronoUnit unit;
            switch (period) {
                case "day":
                    unit = ChronoUnit.DAYS;
                    break;
                case "week":
                    unit = ChronoUnit.WEEKS;
                    break;
                case "month":
                    unit = ChronoUnit.MONTHS;
                    break;
                case "year":
                    unit = ChronoUnit.YEARS;
                    break;
                default:
                    unit = ChronoUnit.DAYS; // default to days if a custom period is provided
                    break;
            }

            // Calculate the total number of days between start and end dates
            long totalDaysBetween = ChronoUnit.DAYS.between(localStartDate, localEndDate);
            // period is longer than the days between start and end
            if ((unit != ChronoUnit.DAYS && totalDaysBetween < unit.getDuration().toDays()) ||
                    (unit == ChronoUnit.DAYS && totalDaysBetween < customPeriod)) {
                presenter.prepareFailView("Period is longer than the period between start and end date!");
                return;
            }

            // init data
            PeriodicTransactionOutputData outputData = null;
            LocalDate currentDate = localStartDate;

            while (!currentDate.isAfter(localEndDate)) {
                if (periodTypes.contains(period)) {
                    // inflow transaction
                    PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, currentDate,
                            description, localEndDate, (int) unit.getDuration().toDays());

                    float totalIncome = userAccount.getTotalIncome();
                    income = totalIncome + amount;
                    userAccount.setTotalIncome(income);  // update the total income

                    // update the balance accordingly
                    float balance = userAccount.getTotalBalance();
                    float totalBalance = balance + amount;
                    userAccount.setTotalBalance(totalBalance);

                    outputData = new PeriodicTransactionOutputData(periodicInflow,
                            userAccount.getTotalBalance());
                } else {
                    // inflow transaction
                    PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, currentDate,
                            description, localEndDate, customPeriod);

                    // update the total income
                    float totalIncome = userAccount.getTotalIncome();
                    income = totalIncome + amount;
                    userAccount.setTotalIncome(income);

                    // update the balance accordingly
                    float balance = userAccount.getTotalBalance();
                    float totalBalance = balance + amount;
                    userAccount.setTotalBalance(totalBalance);

                    outputData = new PeriodicTransactionOutputData(periodicInflow,
                            userAccount.getTotalBalance());
                }

                // save transaction
                userDataAccessObject.saveTransaction(null, outputData, true);
                // update the transaction info to user acc database as well
                userDataAccessObject.update(userAccount);

                // Move to the next date based on the period
                if (unit != ChronoUnit.DAYS) {
                    currentDate = currentDate.plus(1, unit);  // increments currentDate by 1 unit
                    outputData.setDate(currentDate);

                } else if (customPeriod == 0) {
                    currentDate = currentDate.plus(1, unit);  // plus the custom days
                    outputData.setDate(currentDate);
                } else {
                    currentDate = currentDate.plusDays(customPeriod);  // plus the custom days
                    outputData.setDate(currentDate);
                }
            }
            presenter.prepareSuccessView(outputData);
        }

        // for outflow
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate localStartDate = null;
            LocalDate localEndDate = null;

            try {
                // now we have to convert both start and end date
                localStartDate = LocalDate.parse(startDate, formatter);
                localEndDate = LocalDate.parse(endDate, formatter);

                // if start date is after end date
                if (localStartDate.isAfter(localEndDate)) {
                    presenter.prepareFailView("Start date after end day, Plz enter again");
                    return;
                }
            } catch (DateTimeParseException e) {  // if anyone of the start end has issue, catch!
                presenter.prepareFailView("Invalid date format! Plz enter again");
                return;
            }

            // if the user did not choose the pre-determined period
            if (!periodTypes.contains(period)) {
                try {
                    // assume the custom period is in days
                    customPeriod = Integer.parseInt(period);
                } catch (NumberFormatException e) {
                    presenter.prepareFailView("The custom period is not in correct format, please enter again!");
                    return;  // fail than do not excute the rest of the code
                }
                if (Integer.parseInt(period) == 0) {
                    presenter.prepareFailView("The custom period can not be 0, please enter again!");
                    return;  // custom days can not be 0
                }
            }

            ChronoUnit unit;
            switch (period) {
                case "day":
                    unit = ChronoUnit.DAYS;
                    break;
                case "week":
                    unit = ChronoUnit.WEEKS;
                    break;
                case "month":
                    unit = ChronoUnit.MONTHS;
                    break;
                case "year":
                    unit = ChronoUnit.YEARS;
                    break;
                default:
                    unit = ChronoUnit.DAYS; // default to days if a custom period is provided
                    break;
            }

            // Calculate the total number of days between start and end dates
            long totalDaysBetween = ChronoUnit.DAYS.between(localStartDate, localEndDate);
            // period is longer than the days between start and end
            if ((unit != ChronoUnit.DAYS && totalDaysBetween < unit.getDuration().toDays()) ||
                    (unit == ChronoUnit.DAYS && totalDaysBetween < customPeriod)) {
                presenter.prepareFailView("Period is longer than the period between start and end date!");
                return;
            }

            // init data
            PeriodicTransactionOutputData outputData = null;
            LocalDate currentDate = localStartDate;

            while (!currentDate.isAfter(localEndDate)) {
                if (periodTypes.contains(period)) {
                    // inflow transaction
                    PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, currentDate,
                            description, localEndDate, (int) unit.getDuration().toDays());

                    // update the total outFlow
                    float totalOutFlow = userAccount.getTotalOutflow();
                    outFlow = totalOutFlow + amount;
                    userAccount.setTotalOutflow(outFlow);

                    // update the balance accordingly
                    float balance = userAccount.getTotalBalance();
                    float totalBalance = balance + amount;
                    userAccount.setTotalBalance(totalBalance);

                    outputData = new PeriodicTransactionOutputData(periodicOutflow,
                            userAccount.getTotalBalance());
                } else {
                    // inflow transaction
                    PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, currentDate,
                            description, localEndDate, customPeriod);

                    // update the total outFlow
                    float totalOutFlow = userAccount.getTotalOutflow();
                    outFlow = totalOutFlow + amount;
                    userAccount.setTotalOutflow(outFlow);

                    // update the balance accordingly
                    float balance = userAccount.getTotalBalance();
                    float totalBalance = balance + amount;
                    userAccount.setTotalBalance(totalBalance);

                    outputData = new PeriodicTransactionOutputData(periodicOutflow,
                            userAccount.getTotalBalance());
                }

                // save transaction
                userDataAccessObject.saveTransaction(null, outputData, true);
                // update the transaction info to user acc database as well
                userDataAccessObject.update(userAccount);

                // Move to the next date based on the period
                if (unit != ChronoUnit.DAYS) {
                    currentDate = currentDate.plus(1, unit);  // increments currentDate by 1 unit
                    outputData.setDate(currentDate);
                } else if (customPeriod == 0) {
                    currentDate = currentDate.plus(1, unit);  // plus the custom days
                    outputData.setDate(currentDate);
                } else {
                    currentDate = currentDate.plusDays(customPeriod);  // plus the custom days
                    outputData.setDate(currentDate);
                }
            }
            presenter.prepareSuccessView(outputData);
        }
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
            return Float.MIN_VALUE; // Return a sentinel value indicating failure
        }
    }

    /**
     * Checks if the provided user input is valid (not null or empty).
     *
     * @param userInfo the user input to check
     * @return true if the user input is valid, false otherwise
     */
    private boolean checkValid(String userInfo) {
        if (userInfo == null || userInfo.isEmpty()) {
            return false;
        }
        return true;
    }

    private ArrayList<String> createPeriodTypes(){
        ArrayList<String> periodTypes = new ArrayList<>();
        periodTypes.add("day");
        periodTypes.add("week");
        periodTypes.add("month");
        periodTypes.add("year");
        return periodTypes;
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null; // Return null indicating failure
        }
    }
}