package use_case;

import data_access.UserAccountDataAccessInterface;
import entity.Transaction;
import entity.UserAccount;

public class OneTimeTransactionInteractor implements OneTimeTransactionInputBoundary {
    final UserAccountDataAccessInterface userDataAccessObject;
    final OneTimeTransactionOutputBoundary presenter;
    final UserAccount userAccount;

    public OneTimeTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                        OneTimeTransactionOutputBoundary oneTimeTransactionOutputBoundary,
                                        UserAccount userAccount) {
        this.userDataAccessObject = userAccountDataAccessInterface;
        this.presenter = oneTimeTransactionOutputBoundary;
        this.userAccount = userAccount;
    }

    @Override
    public void execute(OneTimeTransactionInputData oneTimeTransactionInputData) {
        String identification = oneTimeTransactionInputData.getIdentification();
        float transactionAmount = oneTimeTransactionInputData.getTransactionAmount();
        String transactionDate = oneTimeTransactionInputData.getTransactionDate();
        String transactionDescription = oneTimeTransactionInputData.getTransactionDescription();
        String transactionCategory = oneTimeTransactionInputData.getTransactionCategory();
        String transactionNotes = oneTimeTransactionInputData.getTransactionNotes();

        // Assuming user is already logged in
//        boolean userExists = userDataAccessObject.existById(identification);

//        if (!userExists) {
//            // Handle case where user does not exist
//            presenter.prepareFailView("User not found!"); // Example method in presenter to handle this case
//            return;
//        }
        boolean isInflow = transactionAmount >= 0.0;  // if amount < 0 then inflow = false

        // Fetch user account based on identification
        UserAccount userAccount = userDataAccessObject.getById(identification);

        float income = 0.0f;
        float outFlow = 0.0f;

        // update the inflow and outflow
        if (isInflow) {
            float totalIncome = userAccount.getTotalIncome();
            income = totalIncome + transactionAmount;
            userAccount.setTotalIncome(income);  // update the total income

            // update the balance accordingly
            float balance = userAccount.getTotalBalance();
            float totalBalance = balance + income;
            userAccount.setTotalBalance(totalBalance);
        }
        else {
            float totalOutflow = userAccount.getTotalOutflow();
            outFlow = totalOutflow + transactionAmount;
            userAccount.setTotalOutflow(outFlow);  // update the total outflow

            // update the balance accordingly
            float balance = userAccount.getTotalBalance();
            float totalBalance = balance - outFlow;
            userAccount.setTotalBalance(totalBalance);
        }

        // Prepare output data
        OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(
                transactionAmount, transactionDate, transactionDescription, transactionCategory, transactionNotes);
        presenter.prepareSuccessView(outputData);
    }
}