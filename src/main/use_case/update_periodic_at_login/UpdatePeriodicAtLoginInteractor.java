package use_case.update_periodic_at_login;

import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class UpdatePeriodicAtLoginInteractor implements UpdatePeriodicAtLoginInputBoundary {
    private final UserAccountDataAccessInterface userDataAccessObject;

    public UpdatePeriodicAtLoginInteractor(UserAccountDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(UpdatePeriodicAtLoginInputData updatePeriodicAtLoginInputData) {
        // Get the information from the input data
        String userId = updatePeriodicAtLoginInputData.getIdentification();
        LocalDate currentDate = updatePeriodicAtLoginInputData.getCurrentDate();

        // get the user account, transactions, and the last login date
        UserAccount userAccount = userDataAccessObject.getById(userId);
        List<Transaction> transactions = userDataAccessObject.readTransactions(userId);
        LocalDate lastLoginDate = userAccount.getLastLoginDate();


        for (Transaction transaction : transactions) {
            // If the transaction is the most recently updated periodic transaction
            if ((transaction instanceof PeriodicTransaction) && (transaction.getDate() == lastLoginDate)) {

                // Get transaction information
                PeriodicTransaction periodicTransaction = (PeriodicTransaction) transaction;
                LocalDate endDate = periodicTransaction.getEndDate();
                LocalDate lastRecordedDate = periodicTransaction.getDate();
                LocalDate date = lastRecordedDate.plusDays(1); // start from the day after the last recorded date

                // Ensure we do not go beyond currentDate or endDate
                while (!date.isAfter(currentDate) && !date.isAfter(endDate)) {
                    if (periodicTransaction.getAmount() >= 0) {
                        processInflow(userAccount, periodicTransaction, userDataAccessObject, date);
                    } else {
                        processInflow(userAccount, periodicTransaction, userDataAccessObject, date);
                    }

                    // update date
                    date = date.plusDays(periodicTransaction.getPeriod());

                }
            }
        }
        userAccount.setLastLoginDate(currentDate);
        userDataAccessObject.update(userAccount);

    }

    private void processInflow(UserAccount userAccount, PeriodicTransaction periodicTransaction, UserAccountDataAccessInterface userDataAccessObject, LocalDate date){

        // Create new periodic inflow
        PeriodicInflow periodicInflow = new PeriodicInflow(
                userAccount.getIdentification(),
                periodicTransaction.getAmount(),
                periodicTransaction.getStartDate(),
                periodicTransaction.getDescription(),
                periodicTransaction.getEndDate(),
                periodicTransaction.getPeriod(),
                periodicTransaction.getTransactionCategory());

        // update date
        periodicInflow.setDate(date);

        // Create a new PeriodicInflow object
        float totalIncome = userAccount.getTotalIncome() + periodicTransaction.getAmount();
        userAccount.setTotalIncome(totalIncome);

        // Update the user's total income and balance
        float totalBalance = userAccount.getTotalBalance() + periodicTransaction.getAmount();
        userAccount.setTotalBalance(totalBalance);

        // Update through the DAO
        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicInflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(userAccount);
    }

    private void processOutflow(UserAccount userAccount, PeriodicTransaction periodicTransaction, UserAccountDataAccessInterface userDataAccessObject, LocalDate date){

        // Create new periodic outflow
        PeriodicOutflow periodicOutflow = new PeriodicOutflow(
                userAccount.getIdentification(),
                periodicTransaction.getAmount(),
                periodicTransaction.getStartDate(),
                periodicTransaction.getDescription(),
                periodicTransaction.getEndDate(),
                periodicTransaction.getPeriod(),
                periodicTransaction.getTransactionCategory());

        // update date
        periodicOutflow.setDate(date);

        // Create a new PeriodicInflow object
        float totalOutflow = userAccount.getTotalOutflow() + periodicTransaction.getAmount();
        userAccount.setTotalOutflow(totalOutflow);

        // Update the user's total income and balance
        float totalBalance = userAccount.getTotalBalance() + periodicTransaction.getAmount();
        userAccount.setTotalBalance(totalBalance);

        // Update through the DAO
        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(periodicOutflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(userAccount);
    }

}