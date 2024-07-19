package use_case;

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


public class PeriodicTransactionInteractor implements PeriodicTransactionInputBoundary {
    final UserAccountDataAccessInterface userDataAccessObject;
    final PeriodicTransactionOutputBoundary presenter;
    final UserAccount userAccount;

    public PeriodicTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                         PeriodicTransactionOutputBoundary periodicTransactionOutputBoundary,
                                         UserAccount userAccount) {
        this.userDataAccessObject = userAccountDataAccessInterface;
        this.presenter = periodicTransactionOutputBoundary;
        this.userAccount = userAccount;
    }

    @Override
    public void execute(PeriodicTransactionInputData periodicTransactionInputData) {
        String identification = periodicTransactionInputData.getIdentification();
        float amount = periodicTransactionInputData.getTransactionAmount();
        String endDate = periodicTransactionInputData.getTransactionEndDate();
        String description = periodicTransactionInputData.getTransactionDescription();
        String startDate = periodicTransactionInputData.getTransactionStartDate();
        String period = periodicTransactionInputData.getTransactionPeriod();

        // Fetch user account based on identification
        UserAccount userAccount = userDataAccessObject.getById(identification);

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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy").withResolverStyle(ResolverStyle.STRICT);
            LocalDate localStartDate = null;
            LocalDate localEndDate = null;

            try {
                // Print debug info
                System.out.println("Start date string: " + startDate);
                System.out.println("End date string: " + endDate);
                // Print each character code to check for hidden characters
                for (int i = 0; i < startDate.length(); i++) {
                    System.out.println("Character " + i + ": " + startDate.charAt(i) + " (code: " + (int) startDate.charAt(i) + ")");
                }
                for (int i = 0; i < endDate.length(); i++) {
                    System.out.println("Character " + i + ": " + endDate.charAt(i) + " (code: " + (int) endDate.charAt(i) + ")");

                // now we have to convert both start and end date
                localStartDate = LocalDate.parse(startDate, formatter);
                System.out.println("Parsed start date: " + localStartDate);
                localEndDate = LocalDate.parse(endDate, formatter);
                System.out.println("Parsed end date: " + localEndDate);
                if (localStartDate.isAfter(localEndDate)) {
                    presenter.prepareFailView("Start date after end day, Plz enter again");
                    return;
                }
            } catch (DateTimeParseException e) {  // if anyone of the start end has issue, catch!
                // debug
                System.out.println("Failed to parse start or end date:");
                e.printStackTrace(); // Print stack trace for detailed debugging

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

            LocalDate currentDate = localStartDate;
            while (!currentDate.isAfter(localEndDate)) {
                // Add the transaction for the current date

                PeriodicTransactionOutputData outputData;
                if (periodTypes.contains(period)) {
                    // inflow transaction
                    PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, localStartDate,
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
                    PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, localStartDate,
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
        }

        // for outflow
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy").withResolverStyle(ResolverStyle.STRICT);
            LocalDate localStartDate = null;
            LocalDate localEndDate = null;

            try {
                // now we have to convert both start and end date
                localStartDate = LocalDate.parse(startDate, formatter);
                localEndDate = LocalDate.parse(endDate, formatter);
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

            LocalDate currentDate = localStartDate;
            while (!currentDate.isAfter(localEndDate)) {
                // Add the transaction for the current date

                PeriodicTransactionOutputData outputData;
                if (periodTypes.contains(period)) {
                    // inflow transaction
                    PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, localStartDate,
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
                    PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, localStartDate,
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
        }
    }
}