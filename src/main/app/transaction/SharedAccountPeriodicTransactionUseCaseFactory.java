package app.transaction;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionPresenter;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInteractor;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputBoundary;
import view.transaction.periodic.SharedAccountPeriodicTransactionView;

import javax.swing.*;
import java.io.IOException;

public class SharedAccountPeriodicTransactionUseCaseFactory {
    private SharedAccountPeriodicTransactionUseCaseFactory() {}

    public static SharedAccountPeriodicTransactionView create(ViewManagerModel viewManagerModel,
                                                              SharedAccountPeriodicTransactionViewModel viewModel) {
        try {
            SharedAccountPeriodicTransactionController sharedAccountPeriodicTransactionController =
                    createSharedAccountPeriodicUseCase(viewManagerModel, viewModel);
            return new SharedAccountPeriodicTransactionView(viewModel, sharedAccountPeriodicTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open shared account data file.");
        }
        return null;
    }

    private static SharedAccountPeriodicTransactionController createSharedAccountPeriodicUseCase(
            ViewManagerModel viewManager,
            SharedAccountPeriodicTransactionViewModel viewModel) throws IOException {

        // Use SharedAccountDataAccessInterface for accessing shared account data
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getShareAccountDataAccessObject();

        // Presenter for handling shared account periodic transactions
        SharedAccountPeriodicTransactionOutputBoundary presenter =
                new SharedAccountPeriodicTransactionPresenter(viewModel, viewManager);

        // Retrieve shared account by its ID
        SharedAccount sharedAccount = dataAccessObject.getById(viewManager.getUserId());

        // Interactor for shared account periodic transactions
        SharedAccountPeriodicTransactionInteractor interactor =
                new SharedAccountPeriodicTransactionInteractor(dataAccessObject, sharedAccount, presenter);

        // Return the controller with the interactor and view model
        return new SharedAccountPeriodicTransactionController(interactor, viewModel);
    }
}

