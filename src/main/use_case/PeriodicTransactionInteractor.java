package use_case;

import data_access.UserAccountDataAccessInterface;

import entity.PeriodicInflow;
import entity.PeriodicTransaction;
import entity.Transaction;
import entity.UserAccount;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        String duration = periodicTransactionInputData.getTransactionDuration();

        // Fetch user account based on identification
        UserAccount userAccount = userDataAccessObject.getById(identification);

        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false
        float income = 0.0f;
        float outFlow = 0.0f;
        ArrayList<String> durationTypes = new ArrayList<>();
        durationTypes.add("day");
        durationTypes.add("week");
        durationTypes.add("month");
        durationTypes.add("year");
        int customDuration = 0;

        // update the inflow and outflow
        if (isInflow) {
            float totalIncome = userAccount.getTotalIncome();
            income = totalIncome + amount;
            userAccount.setTotalIncome(income);  // update the total income

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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

            // if the user did not chose the pre-determined duration
            if (!durationTypes.contains(duration)) {
                try {
                    // assume the custom duration is in days
                    customDuration = Integer.parseInt(duration);
                } catch (NumberFormatException e) {
                    presenter.prepareFailView("The custom duration is not in correct format, please enter again!");
                    return;  // fail than do not excute the rest of the code
                }
            }

            ChronoUnit unit;
            switch (duration) {
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
                    unit = ChronoUnit.DAYS; // default to days if a custom duration is provided
                    break;
            }

            // Calculate the total number of days between start and end dates
            long totalDaysBetween = ChronoUnit.DAYS.between(localStartDate, localEndDate);
            // duration is longer than the days between start and end
            if ((unit != ChronoUnit.DAYS && totalDaysBetween < unit.getDuration().toDays()) ||
                    (unit == ChronoUnit.DAYS && totalDaysBetween < customDuration)) {
                presenter.prepareFailView("Duration is longer than the period between start and end date!");
                return;
            }

            LocalDate currentDate = localStartDate;
            while (!currentDate.isAfter(localEndDate)) {
                // Add the transaction for the current date

                if (durationTypes.contains(duration)) {
                    // inflow transaction
                    PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, localStartDate,
                            description, localEndDate, (int) unit.getDuration().toDays());
                } else {
                    // inflow transaction
                    PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, localStartDate,
                            description, localEndDate, customDuration);
                }

                // update the balance accordingly
                float balance = userAccount.getTotalBalance();
                float totalBalance = balance + income;
                userAccount.setTotalBalance(totalBalance);

                PeriodicTransactionOutputData outputData;  //这里period output要改成利用periodic inflow和outflow的地方 明天醒来再说
                // 放在什么位置也很不好说，感觉这是一个很容易出bug的地方
//                = new PeriodicTransactionOutputData();
                presenter.prepareSuccessView(outputData);

                // Move to the next date based on the duration
                if (unit != ChronoUnit.DAYS) {
                    currentDate = currentDate.plus(1, unit);  // increments currentDate by 1 unit
                } else {
                    currentDate = currentDate.plusDays(customDuration);  // plus the custom days
                }
            }

        } else{

        }
    }
}