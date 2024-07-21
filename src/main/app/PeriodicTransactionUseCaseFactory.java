package app;

import data_access.DAOFactory;
import data_access.UserAccountDataAccessInterface;
import entity.UserAccount;
import interface_adaptors.*;
import use_case.OneTimeTransactionInteractor;
import use_case.OneTimeTransactionOutputBoundary;
import use_case.PeriodicTransactionInteractor;
import use_case.PeriodicTransactionOutputBoundary;
import view.OneTimeTransactionView;
import view.PeriodicTransactionView;

import javax.swing.*;
import java.io.IOException;

public class PeriodicTransactionUseCaseFactory {
    private PeriodicTransactionUseCaseFactory() {}

    public static PeriodicTransactionView create(ViewManagerModel viewManagerModel,
                                                 PeriodicTransactionViewModel viewModel) {
        try {
            PeriodicTransactionController periodicTransactionController = createUserPeriodicUseCase(viewManagerModel,
                    viewModel);
            return new PeriodicTransactionView(viewModel, periodicTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static PeriodicTransactionController createUserPeriodicUseCase(ViewManagerModel viewManager,
                                                                           PeriodicTransactionViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getOneTimeTransactionDAO();
        PeriodicTransactionOutputBoundary presenter = new PeriodicTransactionPresenter(viewModel, viewManager);
        UserAccount userAccount = dataAccessObject.getById(viewManager.getUserId());
        PeriodicTransactionInteractor interactor = new PeriodicTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new PeriodicTransactionController(interactor, viewModel);
    }

}
