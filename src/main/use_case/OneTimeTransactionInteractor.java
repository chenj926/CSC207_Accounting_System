package use_case;

import data_access.UserAccountDataAccessInterface;
import entity.UserAccount;
import entity.OneTimeInflow;
import entity.OneTimeOutflow;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OneTimeTransactionInteractor implements OneTimeTransactionInputBoundary {
    final UserAccountDataAccessInterface userDataAccessObject;
    final OneTimeTransactionOutputBoundary presenter;
    private UserAccount userAccount;

    public OneTimeTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                        OneTimeTransactionOutputBoundary oneTimeTransactionOutputBoundary) {
        this.userDataAccessObject = userAccountDataAccessInterface;
        this.presenter = oneTimeTransactionOutputBoundary;
    }

    @Override
    public void execute(OneTimeTransactionInputData oneTimeTransactionInputData) {
        String identification = oneTimeTransactionInputData.getIdentification();
        float amount = oneTimeTransactionInputData.getTransactionAmount();
        String date = oneTimeTransactionInputData.getTransactionDate();
        String description = oneTimeTransactionInputData.getTransactionDescription();
        String category = oneTimeTransactionInputData.getTransactionCategory();

        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false

        // Fetch user account based on identification
        this.userAccount = userDataAccessObject.getById(identification);


        float income = 0.0f;
        float outFlow = 0.0f;

        // update the inflow and outflow
        if (isInflow) {
            float totalIncome = userAccount.getTotalIncome();
            income = totalIncome + amount;
            userAccount.setTotalIncome(income);  // update the total income

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = null;
            try {
                localDate = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                presenter.prepareFailView("Invalid date format! Plz enter again");
                return;
            }
            OneTimeInflow oneTimeInflow = new OneTimeInflow(identification, amount, localDate, description, category);

            // update the balance accordingly
            float balance = userAccount.getTotalBalance();
            float totalBalance = balance + income;
            userAccount.setTotalBalance(totalBalance);
            // Prepare output data
            OneTimeTransactionOutputData outputData = new OneTimeTransactionOutputData(oneTimeInflow, userAccount.getTotalBalance());
            presenter.prepareSuccessView(outputData);
        }
        else {
            float totalOutflow = userAccount.getTotalOutflow();
            outFlow = totalOutflow + amount;
            userAccount.setTotalOutflow(outFlow);  // update the total outflow

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
            presenter.prepareSuccessView(outputData);
        }
    }
}