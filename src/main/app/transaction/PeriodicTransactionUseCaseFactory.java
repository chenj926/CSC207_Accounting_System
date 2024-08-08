package app.transaction;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import interface_adaptors.*;
import interface_adaptors.transaction.periodic.UserAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.UserAccountPeriodicTransactionPresenter;
import interface_adaptors.transaction.periodic.UserAccountPeriodicTransactionViewModel;
import use_case.transaction.periodic.UserAccountPeriodicTransactionInteractor;
import use_case.transaction.periodic.UserAccountPeriodicTransactionOutputBoundary;
import view.transaction.periodic.PeriodicTransactionView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for creating components related to the periodic transaction use case.
 * <p>
 * This factory is responsible for assembling the necessary components, such as the view,
 * controller, interactor, and presenter, for handling periodic transactions associated with user accounts.
 * </p>
 * @author Eric
 * @author Zella
 */
public class PeriodicTransactionUseCaseFactory {
    private PeriodicTransactionUseCaseFactory() {}

    /**
     * Creates and returns the view for the periodic transaction use case.
     * <p>
     * This method sets up the necessary components, including the controller and view model,
     * for handling periodic transactions and returns the assembled view.
     * </p>
     *
     * @param viewManagerModel the model responsible for managing view transitions
     * @param viewModel the view model that holds the state of the periodic transaction
     * @return the assembled PeriodicTransactionView
     */
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

    /**
     * Creates and returns the controller for handling periodic transactions.
     * <p>
     * This method sets up the interactor and presenter required to manage periodic transactions,
     * and returns a controller to facilitate interactions between the user and the use case.
     * </p>
     *
     * @param viewManager the model responsible for managing view transitions
     * @param viewModel the view model that holds the state of the periodic transaction
     * @return the assembled UserAccountPeriodicTransactionController
     * @throws IOException if an error occurs while accessing the user data file
     */
    private static UserAccountPeriodicTransactionController createUserPeriodicUseCase(ViewManagerModel viewManager,
                                                                                      UserAccountPeriodicTransactionViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getPeriodicTransactionDAO();
        UserAccountPeriodicTransactionOutputBoundary presenter = new UserAccountPeriodicTransactionPresenter(viewModel, viewManager);
        UserAccount userAccount = dataAccessObject.getById(viewManager.getUserId());
        UserAccountPeriodicTransactionInteractor interactor = new UserAccountPeriodicTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new UserAccountPeriodicTransactionController(interactor, viewModel);
    }

}
