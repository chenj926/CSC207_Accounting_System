package use_case.transaction.one_time;

import data_access.account.AccountDataAccessInterface;
import entity.account.Account;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.TransactionInteractor;

import java.time.LocalDate;

/**
 * Abstract class for handling one-time transactions. This class defines the common methods
 * for processing inflow and outflow transactions for different account types.
 *
 * @param <A> The type of account.
 * @param <O> The type of one-time transaction output data.
 */
public abstract class OneTimeTransactionInteractor<
        DAO extends AccountDataAccessInterface<A, O, P>,
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P,
        I extends OneTimeTransactionInputData> extends TransactionInteractor<DAO, A, O, P> implements OneTimeTransactionInputBoundary<I> {

    protected final OneTimeTransactionOutputBoundary<O> presenter;

    /**
     * Constructs an AbstractOneTimeTransactionInteractor object.
     *
     * @param dataAccessInterface the data access interface for account data
     * @param presenter           the output boundary for presenting the one-time transaction results
     * @param account             the account associated with the transaction
     */
    protected OneTimeTransactionInteractor(DAO dataAccessInterface,
                                           OneTimeTransactionOutputBoundary<O> presenter,
                                           A account) {
        super(dataAccessInterface, account);
        this.presenter = presenter;
    }

    @Override
    public void execute(I inputData) {
        String identification = inputData.getId();
        String stringAmount = inputData.getTransactionAmount();
        String date = inputData.getTransactionDate();
        String description = inputData.getTransactionDescription();
        String category = inputData.getTransactionCategory();

        if (!checkValid(stringAmount) || !checkValid(date) || !checkValid(description) || !checkValid(category)) {
            presenter.prepareFailView("Please do NOT have any empty inputs!");
            return;
        }

        float amount = parseAmount(stringAmount);
        if (amount == Float.MIN_VALUE) {
            presenter.prepareFailView("Incorrect amount! Please ONLY enter numbers.");
            return;
        }

        LocalDate localDate = parseDate(date);
        if (localDate == null) {
            presenter.prepareFailView("Invalid date format! Please enter again.");
            return;
        }

        boolean isInflow = amount >= 0.0;

        if (isInflow) {
            processInflow(identification, amount, localDate, description, category);
        } else {
            processOutflow(identification, amount, localDate, description, category);
        }
    }

    private void processInflow(String identification, float amount, LocalDate date, String description, String category) {
        float totalIncome = this.account.getTotalIncome() + amount;
        this.account.setTotalIncome(totalIncome);  // update the total income

        // prepare inflow
        OneTimeInflow oneTimeInflow = new OneTimeInflow(identification, amount, date, description, category);
        float totalBalance = this.account.getTotalBalance() + amount;
        this.account.setTotalBalance(totalBalance);  // Update the balance accordingly

        // Prepare output data
        O outputData = createOutputData(oneTimeInflow);

        // Save this transaction
        userDataAccessObject.saveTransaction(outputData, null,false);
        // update the transaction info to user acc database as well
        userDataAccessObject.update(this.account);
        presenter.prepareSuccessView(outputData);
    }

    private void processOutflow(String identification, float amount, LocalDate date, String description, String category) {
        float totalOutflow = this.account.getTotalOutflow() + amount;
        this.account.setTotalOutflow(totalOutflow);  // update the total outflow

        // prepare outflow
        OneTimeOutflow oneTimeOutflow = new OneTimeOutflow(identification, amount, date, description, category);
        float totalBalance = this.account.getTotalBalance() + amount;
        this.account.setTotalBalance(totalBalance);  // Update the balance accordingly

        // Prepare output data
        O outputData = createOutputData(oneTimeOutflow);

        // Save this transaction
        userDataAccessObject.saveTransaction(outputData, null, false);
        // update the transaction info to user acc database as well
        userDataAccessObject.update(this.account);
        presenter.prepareSuccessView(outputData);
    }

    protected abstract O createOutputData(OneTimeTransaction transaction);

}
