package use_case.transaction.one_time;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;
import use_case.transaction.TransactionInteractor;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;

/**
 * The UserAccountOneTimeTransactionInteractor class implements the OneTimeTransactionInputBoundary interface.
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
public class UserAccountOneTimeTransactionInteractor extends TransactionInteractor<
        UserAccountDataAccessInterface,
        UserAccount,
        OneTimeTransactionOutputData,
        PeriodicTransactionOutputData>
        implements OneTimeTransactionInputBoundary {
    private final OneTimeTransactionOutputBoundary presenter;


    /**
     * Constructs a UserAccountOneTimeTransactionInteractor object with the specified data access interface,
     * output boundary, and user account.
     *
     * @param userAccountDataAccessInterface the data access interface for user data
     * @param oneTimeTransactionOutputBoundary the output boundary for presenting the one-time transaction results
     * @param userAccount the user account associated with the transaction
     */
    public UserAccountOneTimeTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                                   OneTimeTransactionOutputBoundary oneTimeTransactionOutputBoundary,
                                                   UserAccount userAccount) {
        super(userAccountDataAccessInterface, userAccount);
        this.presenter = oneTimeTransactionOutputBoundary;
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
        String identification = oneTimeTransactionInputData.getId();
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
        float totalIncome = this.account.getTotalIncome() + amount;
        this.account.setTotalIncome(totalIncome);  // update the total income

        // prepare inflow
        OneTimeInflow oneTimeInflow = new OneTimeInflow(identification, amount, date, description, category);
        float totalBalance = this.account.getTotalBalance() + amount;
        this.account.setTotalBalance(totalBalance);  // Update the balance accordingly

        // Prepare output data
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeInflow);

        // Save this transaction
        userDataAccessObject.saveTransaction(outputData, null,false);
        // update the transaction info to user acc database as well
        userDataAccessObject.update(this.account);
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
        float totalOutflow = this.account.getTotalOutflow() + amount;
        this.account.setTotalOutflow(totalOutflow);  // update the total outflow

        // prepare outflow
        OneTimeOutflow oneTimeOutflow = new OneTimeOutflow(identification, amount, date, description, category);
        float totalBalance = this.account.getTotalBalance() + amount;
        this.account.setTotalBalance(totalBalance);  // Update the balance accordingly

        // Prepare output data
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeOutflow);

        // Save this transaction
        userDataAccessObject.saveTransaction(outputData, null, false);
        // update the transaction info to user acc database as well
        userDataAccessObject.update(this.account);
        presenter.prepareSuccessView(outputData);
    }

}