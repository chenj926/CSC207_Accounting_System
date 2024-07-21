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
                                        OneTimeTransactionOutputBoundary oneTimeTransactionOutputBoundary,
                                        UserAccount userAccount) {
        this.userDataAccessObject = userAccountDataAccessInterface;
        this.presenter = oneTimeTransactionOutputBoundary;
        this.userAccount = userAccount;
    }

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

        // check if the entered amount is correct
        float amount = 0.00f;
        // to see if amount is proper float
        try {
            amount = Float.parseFloat(stringAmount);
        } catch (NumberFormatException e) {
            presenter.prepareFailView("Incorrect amount! please ONLY enter number");
            return;
        }

        // format the input to .2 decimal place
        String formattedAmount = String.format("%.2f", amount);
        amount = Float.parseFloat(formattedAmount);

        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false

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

    public boolean checkValid(String userInfo) {
        if (userInfo == null || userInfo.isEmpty()) {
            return false;
        }
        return true;
    }
}