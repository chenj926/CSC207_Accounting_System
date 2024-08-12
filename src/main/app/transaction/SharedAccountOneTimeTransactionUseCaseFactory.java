package app.transaction;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionViewModel;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputBoundary;
import view.transaction.one_time.shared_account.SharedAccountOneTimeTransactionView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for creating components related to the shared account one-time transaction use case.
 * <p>
 * This factory is responsible for assembling the necessary components, such as the view,
 * controller, interactor, and presenter, for handling one-time transactions associated with shared accounts.
 * </p>
 * @author Eric
 * @author Zella
 */
public class SharedAccountOneTimeTransactionUseCaseFactory {
    private SharedAccountOneTimeTransactionUseCaseFactory() {}

    /**
     * Creates and returns the view for the shared account one-time transaction use case.
     * <p>
     * This method sets up the necessary components, including the controller and view model,
     * for handling one-time transactions and returns the assembled view.
     * </p>
     *
     * @param viewManagerModel the model responsible for managing view transitions
     * @param oneTimeTransactionViewModel the view model that holds the state of the one-time transaction
     * @return the assembled SharedAccountOneTimeTransactionView
     */
    public static SharedAccountOneTimeTransactionView create(ViewManagerModel viewManagerModel,
                                                SharedAccountOneTimeTransactionViewModel oneTimeTransactionViewModel) {
        try {
            SharedAccountOneTimeTransactionController oneTimeTransactionController = createSharedAccountOneTimeUseCase(viewManagerModel,
                    oneTimeTransactionViewModel);
            return new SharedAccountOneTimeTransactionView(oneTimeTransactionViewModel, oneTimeTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and returns the controller for handling shared account one-time transactions.
     * <p>
     * This method sets up the interactor and presenter required to manage shared account one-time transactions,
     * and returns a controller to facilitate interactions between the user and the use case.
     * </p>
     *
     * @param viewManagerModel the model responsible for managing view transitions
     * @param oneTimeTransactionViewModel the view model that holds the state of the one-time transaction
     * @return the assembled SharedAccountOneTimeTransactionController
     * @throws IOException if an error occurs while accessing the user data file
     */
    private static SharedAccountOneTimeTransactionController createSharedAccountOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                                         SharedAccountOneTimeTransactionViewModel oneTimeTransactionViewModel) throws IOException {
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountOneTimeTransactionOutputBoundary presenter = new SharedAccountOneTimeTransactionPresenter(oneTimeTransactionViewModel, viewManagerModel);
        SharedAccount sharedAccount = dataAccessObject.getById(viewManagerModel.getUserId());
        SharedAccountOneTimeTransactionInteractor interactor = new SharedAccountOneTimeTransactionInteractor(dataAccessObject, presenter, sharedAccount);
        return new SharedAccountOneTimeTransactionController(interactor, oneTimeTransactionViewModel);
    }

}



