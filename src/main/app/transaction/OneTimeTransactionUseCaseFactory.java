package app.transaction;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import interface_adaptors.*;
import interface_adaptors.transaction.one_time.UserAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.UserAccountOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.UserAccountOneTimeTransactionViewModel;
import use_case.transaction.one_time.UserAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.UserAccountOneTimeTransactionOutputBoundary;
import view.transaction.one_time.OneTimeTransactionView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for creating components related to the one-time transaction use case.
 * <p>
 * This factory is responsible for assembling the necessary components, such as the view,
 * controller, interactor, and presenter, for handling one-time transactions associated with user accounts.
 * </p>
 * @author Eric
 * @author Zella
 */
public class OneTimeTransactionUseCaseFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private OneTimeTransactionUseCaseFactory() {}

    /**
     * Creates and returns the view for the one-time transaction use case.
     * <p>
     * This method sets up the necessary components, including the controller and view model,
     * for handling one-time transactions and returns the assembled view.
     * </p>
     *
     * @param viewManagerModel the model responsible for managing view transitions
     * @param userAccountOneTimeTransactionViewModel the view model that holds the state of the one-time transaction
     * @return the assembled OneTimeTransactionView
     */
    public static OneTimeTransactionView create(ViewManagerModel viewManagerModel,
                                                UserAccountOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel) {
        try {
            UserAccountOneTimeTransactionController userAccountOneTimeTransactionController = createUserOneTimeUseCase(viewManagerModel,
                    userAccountOneTimeTransactionViewModel);
            return new OneTimeTransactionView(userAccountOneTimeTransactionViewModel, userAccountOneTimeTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and returns the controller for handling one-time transactions.
     * <p>
     * This method sets up the interactor and presenter required to manage one-time transactions,
     * and returns a controller to facilitate interactions between the user and the use case.
     * </p>
     *
     * @param viewManagerModel the model responsible for managing view transitions
     * @param userAccountOneTimeTransactionViewModel the view model that holds the state of the one-time transaction
     * @return the assembled UserAccountOneTimeTransactionController
     * @throws IOException if an error occurs while accessing the user data file
     */
    private static UserAccountOneTimeTransactionController createUserOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                                                    UserAccountOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getOneTimeTransactionDAO();
        UserAccountOneTimeTransactionOutputBoundary presenter = new UserAccountOneTimeTransactionPresenter(userAccountOneTimeTransactionViewModel, viewManagerModel);

        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        UserAccountOneTimeTransactionInteractor interactor = new UserAccountOneTimeTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new UserAccountOneTimeTransactionController(interactor, userAccountOneTimeTransactionViewModel);
    }
}

