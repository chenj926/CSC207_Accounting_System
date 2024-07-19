package app;

import data_access.*;
import entity.*;
import interface_adaptors.*;
import use_case.*;
import view.*;

import javax.swing.*;

public class TransactionUseCaseFactory {

    private TransactionUseCaseFactory() {}

    public static TransactionView createTransactionView(ViewManagerModel viewManagerModel, TransactionViewModel transactionViewModel) {
        try {
            // Setup the DAO
            UserSignupDataAccessInterface userDataAccessObject = DAOFactory.getUserSignupDataAccessObject();

            // Mock user account for the purpose of creating interactors
            UserAccount mockUserAccount = new UserAccount("mockUsername", "mockPassword", "mockId");

            // Create One-Time Transaction Components
            OneTimeTransactionOutputBoundary oneTimePresenter = new OneTimeTransactionPresenter(new OneTimeTransactionViewModel(), transactionViewModel);
            OneTimeTransactionInputBoundary oneTimeInteractor = new OneTimeTransactionInteractor((UserAccountDataAccessInterface) userDataAccessObject, (OneTimeTransactionOutputBoundary) oneTimePresenter, mockUserAccount);
            OneTimeTransactionController oneTimeController = new OneTimeTransactionController(oneTimeInteractor);

            // Create Periodic Transaction Components
            PeriodicTransactionOutputBoundary periodicPresenter = new PeriodicTransactionPresenter(new PeriodicTransactionViewModel(), transactionViewModel);
            PeriodicTransactionInputBoundary periodicInteractor = new PeriodicTransactionInteractor((UserAccountDataAccessInterface) userDataAccessObject, (PeriodicTransactionOutputBoundary) periodicPresenter, mockUserAccount);
            PeriodicTransactionController periodicController = new PeriodicTransactionController(periodicInteractor);

            // Create the combined Transaction Interactor
            TransactionInteractor transactionInteractor = new TransactionInteractor((UserAccountDataAccessInterface) userDataAccessObject, oneTimePresenter, periodicPresenter, oneTimeInteractor, periodicInteractor);
            TransactionController transactionController = new TransactionController(transactionViewModel);

            // Create and return the Transaction View
            return new TransactionView(transactionViewModel, transactionController);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not create transaction view.");
            return null;
        }
    }
}
