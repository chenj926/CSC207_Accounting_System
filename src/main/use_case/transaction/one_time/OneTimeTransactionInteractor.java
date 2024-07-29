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
 * <p>
 * This class is responsible for parsing and validating transaction details such as amount and date,
 * and for determining whether the transaction is an inflow or outflow. It then updates the user's
 * account balance accordingly and interacts with the data access object to save the transaction.
 * </p>
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
     * <p>
     * This method validates the input data, parses the transaction amount and date,
     * and determines whether the transaction is an inflow or outflow. It then updates
     * the user's account balance and interacts with the data access object to save the transaction.
     * </p>
     *
     * @param oneTimeTransactionInputData the input data required for the one-time transaction process
     */
    @Override
    public void execute(OneTimeTransactionInputData oneTimeTransactionInputData) {
        // set up basic vars
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

        // Parse and validate the amount
        float amount = parseAmount(stringAmount);
        // we set float.MIN VAL to be the false output of the helper
        if (amount == Float.MIN_VALUE) {
            presenter.prepareFailView("Incorrect amount! please ONLY enter number");
            return;
        }

        // Parse and validate the date
        LocalDate localDate = parseDate(date);
        // we set null as false return from helper
        if (localDate == null) {
            presenter.prepareFailView("Invalid date format! Plz enter again");
            return;
        }

        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false
        // Update the inflow and outflow
        if (isInflow) {
            processInflow(identification, amount, localDate, description, category);
        }
        else {
            processOutflow(identification, amount, localDate, description, category);
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
            return null; // Return null indicating failure
        }
    }

    /**
     * Processes an inflow transaction.
     * <p>
     * This method updates the user's total income and balance, prepares the inflow transaction,
     * creates the output data, and interacts with the data access object to save the transaction.
     * It also uses the presenter to prepare the success view.
     * </p>
     *
     * @param identification the user's identification
     * @param amount the transaction amount
     * @param date the transaction date
     * @param description the transaction description
     * @param category the transaction category
     */
    private void processInflow(String identification, float amount, LocalDate date, String description, String category) {
        float totalIncome = userAccount.getTotalIncome() + amount;
        userAccount.setTotalIncome(totalIncome);  // update the total income

        // prepare inflow
        OneTimeInflow oneTimeInflow = new OneTimeInflow(identification, amount, date, description, category);
        float totalBalance = userAccount.getTotalBalance() + amount;
        userAccount.setTotalBalance(totalBalance);  // Update the balance accordingly

        // Prepare output data
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeInflow, userAccount.getTotalBalance());

        // Save this transaction
        userDataAccessObject.saveTransaction(outputData, null,false);
        // update the transaction info to user acc database as well
        userDataAccessObject.update(userAccount);
        presenter.prepareSuccessView(outputData);
    }

    /**
     * Processes an outflow transaction.
     * <p>
     * This method updates the user's total outflow and balance, prepares the outflow transaction,
     * creates the output data, and interacts with the data access object to save the transaction.
     * It also uses the presenter to prepare the success view.
     * </p>
     *
     * @param identification the user's identification
     * @param amount the transaction amount
     * @param date the transaction date
     * @param description the transaction description
     * @param category the transaction category
     */
    private void processOutflow(String identification, float amount, LocalDate date, String description, String category) {
        float totalOutflow = userAccount.getTotalOutflow() + amount;
        userAccount.setTotalOutflow(totalOutflow);  // update the total outflow

        // prepare outflow
        OneTimeOutflow oneTimeOutflow = new OneTimeOutflow(identification, amount, date, description, category);
        float totalBalance = userAccount.getTotalBalance() + amount;
        userAccount.setTotalBalance(totalBalance);  // Update the balance accordingly

        // Prepare output data
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeOutflow, totalBalance);

        // Save this transaction
        userDataAccessObject.saveTransaction(outputData, null, false);
        // update the transaction info to user acc database as well
        userDataAccessObject.update(userAccount);
        presenter.prepareSuccessView(outputData);
    }

}