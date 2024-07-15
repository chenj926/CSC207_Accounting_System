package use_case;

import data_access.UserAccountDataAccessInterface;

import entity.PeriodicTransaction;
import entity.Transaction;
import entity.UserAccount;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


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
        float transactionAmount = periodicTransactionInputData.getTransactionAmount();
        String transactionDate = periodicTransactionInputData.getTransactionDate();
        String transactionDescription = periodicTransactionInputData.getTransactionDescription();
        String transactionReccurence = periodicTransactionInputData.getRecurrence();
        // Assuming user is already logged in
        boolean userExists = userDataAccessObject.existById(identification);

//        if (!userExists) {
//            // Handle case where user does not exist
//            presenter.UserNotFound(); // Example method in presenter to handle this case
//            return;
//        }







//        // Fetch user account based on identification
//        UserAccount userAccount = userDataAccessObject.getById(identification);
//        // Record transaction in user account
//        userAccount.getIdentification();
//        userAccount.getUsername();
//        userAccount.getTotalBalance();
//        userAccount.getTotalOutflow();
//        userAccount.getTotalIncome();
//
//
//        // Update the user account in the data store
//        userDataAccessObject.updateBalance(userAccount);
//        userDataAccessObject.updateOutflow(userAccount);
//        userDataAccessObject.updateInflow(userAccount);
//        userDataAccessObject.updateId(userAccount);
//
//        // Prepare output data
//        PeriodicTransactionOutputData outputData = new PeriodicTransactionOutputData(
//                true, identification, transactionAmount, transactionDate, transactionDescription, transactionReccurence);
//        presenter.prepareSuccessView(outputData);
//    }
//}