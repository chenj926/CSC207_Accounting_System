package app.transaction;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import interface_adaptors.*;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionPresenter;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInteractor;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputBoundary;
import view.transaction.periodic.PeriodicTransactionView;

import javax.swing.*;
import java.io.IOException;

public class PeriodicTransactionUseCaseFactory {
    private PeriodicTransactionUseCaseFactory() {}

    public static PeriodicTransactionView create(ViewManagerModel viewManagerModel,
                                                 UserAccountPeriodicTransactionViewModel viewModel) {
        try {
            UserAccountPeriodicTransactionController userAccountPeriodicTransactionController = createUserPeriodicUseCase(viewManagerModel,
                    viewModel);
            return new PeriodicTransactionView(viewModel, userAccountPeriodicTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static UserAccountPeriodicTransactionController createUserPeriodicUseCase(ViewManagerModel viewManager,
                                                                                      UserAccountPeriodicTransactionViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getPeriodicTransactionDAO();
        UserAccountPeriodicTransactionOutputBoundary presenter = new UserAccountPeriodicTransactionPresenter(viewModel, viewManager);
        UserAccount userAccount = dataAccessObject.getById(viewManager.getUserId());
        UserAccountPeriodicTransactionInteractor interactor = new UserAccountPeriodicTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new UserAccountPeriodicTransactionController(interactor, viewModel);
    }

}
