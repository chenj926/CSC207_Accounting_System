package use_case.Transaction;

import data_access.UserAccountDataAccessInterface;

import entity.PeriodicInflow;
import entity.PeriodicOutflow;
import entity.UserAccount;

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

        // check if the entered amount is correct
        float amount = 0.00f;
        // to see if amount is proper float
        try {
            amount = Float.parseFloat(stringAmount);
        } catch (NumberFormatException e) {
            presenter.prepareFailView("Incorrect amount! please ONLY enter number");
            return;
        }

        // format the input to .2 decimal place
        String formattedAmount = String.format("%.2f", amount);
        amount = Float.parseFloat(formattedAmount);


        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false
        float income = 0.0f;
        float outFlow = 0.0f;
        ArrayList<String> periodTypes = new ArrayList<>();
        periodTypes.add("day");
        periodTypes.add("week");
        periodTypes.add("month");
        periodTypes.add("year");
        int customPeriod = 0;

        // update the inflow and outflow
        // for inflow
        if (isInflow) {
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

                // Move to the next date based on the period
                if (unit != ChronoUnit.DAYS) {
                    currentDate = currentDate.plus(1, unit);  // increments currentDate by 1 unit
                    outputData.setDate(currentDate);
                    System.out.println("outputdata:" + outputData);
                } else if (customPeriod == 0) {
                    currentDate = currentDate.plus(1, unit);  // plus the custom days
                    outputData.setDate(currentDate);
                } else {
                    currentDate = currentDate.plusDays(customPeriod);  // plus the custom days
                    outputData.setDate(currentDate);
                }
            }
        }
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
}