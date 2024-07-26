package use_case.transaction.one_time;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * The OneTimeTransactionInteractor class implements the OneTimeTransactionInputBoundary interface.
 * It handles the process of creating a one-time transaction by validating the input data,
 * interacting with the data access layer, and using the presenter to prepare the output views.
 *
 * @author Dana
 * @author Eric
 */
public class OneTimeTransactionInteractor implements OneTimeTransactionInputBoundary {
    final UserAccountDataAccessInterface userDataAccessObject;
    final OneTimeTransactionOutputBoundary presenter;
    private UserAccount userAccount;

    /**
     * Constructs a OneTimeTransactionInteractor object with the specified data access interface,
     * output boundary, and user account.
     *
     * @param userAccountDataAccessInterface the data access interface for user data
     * @param oneTimeTransactionOutputBoundary the output boundary for presenting the one-time transaction results
     * @param userAccount the user account associated with the transaction
     */
    public OneTimeTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                        OneTimeTransactionOutputBoundary oneTimeTransactionOutputBoundary,
                                        UserAccount userAccount) {
        this.userDataAccessObject = userAccountDataAccessInterface;
        this.presenter = oneTimeTransactionOutputBoundary;
        this.userAccount = userAccount;
    }

    /**
     * Executes the one-time transaction process with the given input data.
     *
     * @param oneTimeTransactionInputData the input data required for the one-time transaction process
     */
    @Override
    public void execute(OneTimeTransactionInputData oneTimeTransactionInputData) {
        String identification = userAccount.getIdentification();
        String stringAmount = oneTimeTransactionInputData.getTransactionAmount();
        String date = oneTimeTransactionInputData.getTransactionDate();
        String description = oneTimeTransactionInputData.getTransactionDescription();
        String category = oneTimeTransactionInputData.getTransactionCategory();

        // if user entered empty input in one or more of the input fields
        if(!checkValid(stringAmount) || !checkValid(date) ||
                !checkValid(description) || !checkValid(category)) {
            presenter.prepareFailView("Please do NOT have any empty inputs!");
            return;
        }

        // Check if the entered amount is correct
        float amount = 0.00f;
        // to see if amount is proper float
        try {
            amount = Float.parseFloat(stringAmount);
        } catch (NumberFormatException e) {
            presenter.prepareFailView("Incorrect amount! please ONLY enter number");
            return;
        }

        // Format the input to .2 decimal place
        String formattedAmount = String.format("%.2f", amount);
        amount = Float.parseFloat(formattedAmount);

        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false

        float income = 0.0f;
        float outFlow = 0.0f;

        // Update the inflow and outflow
        if (isInflow) {
            float totalIncome = userAccount.getTotalIncome();
            income = totalIncome + amount;
            userAccount.setTotalIncome(income);  // update the total income

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate localDate = null;
            try {
                localDate = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                presenter.prepareFailView("Invalid date format! Plz enter again");
                return;
            }
            OneTimeInflow oneTimeInflow = new OneTimeInflow(identification, amount, localDate, description, category);

            // Update the balance accordingly
            float balance = userAccount.getTotalBalance();
            float totalBalance = balance + income;
            userAccount.setTotalBalance(totalBalance);

            // Prepare output data
            OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeInflow, userAccount.getTotalBalance());

            // Save this transaction
            userDataAccessObject.saveTransaction(outputData, null,false);
            presenter.prepareSuccessView(outputData);
        }
        else {
            float totalOutflow = userAccount.getTotalOutflow();
            outFlow = totalOutflow + amount;
            userAccount.setTotalOutflow(outFlow);  // update the total outflow

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate localDate = null;
            try {
                localDate = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                presenter.prepareFailView("Invalid date format! Plz enter again");
                return;
            }
            OneTimeOutflow oneTimeOutflow = new OneTimeOutflow(identification, amount, localDate, description, category);

            // update the balance accordingly
            float balance = userAccount.getTotalBalance();
            float totalBalance = balance + outFlow;
            userAccount.setTotalBalance(totalBalance);

            // Prepare output data
            OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeOutflow, userAccount.getTotalBalance());

            // save this transaction
            userDataAccessObject.saveTransaction(outputData, null,false);
            presenter.prepareSuccessView(outputData);
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