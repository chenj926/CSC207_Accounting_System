package app;

import data_access.DAOFactory;
import data_access.UserAccountDataAccessInterface;
import entity.UserAccount;
import interface_adaptors.*;
import interface_adaptors.transaction.one_time.OneTimeTransactionController;
import interface_adaptors.transaction.one_time.OneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;
import use_case.Transaction.OneTimeTransactionInteractor;
import use_case.Transaction.OneTimeTransactionOutputBoundary;
import view.Transaction.OneTimeTransactionView;

import javax.swing.*;
import java.io.IOException;

public class OneTimeTransactionUseCaseFactory {
    private OneTimeTransactionUseCaseFactory() {}

    public static OneTimeTransactionView create(ViewManagerModel viewManagerModel,
                                                OneTimeTransactionViewModel oneTimeTransactionViewModel) {
        try {
            OneTimeTransactionController oneTimeTransactionController = createUserOneTimeUseCase(viewManagerModel,
                    oneTimeTransactionViewModel);
            return new OneTimeTransactionView(oneTimeTransactionViewModel, oneTimeTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static OneTimeTransactionController createUserOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                            OneTimeTransactionViewModel oneTimeTransactionViewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getOneTimeTransactionDAO();
        OneTimeTransactionOutputBoundary presenter = new OneTimeTransactionPresenter(oneTimeTransactionViewModel, viewManagerModel);
        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());
        OneTimeTransactionInteractor interactor = new OneTimeTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new OneTimeTransactionController(interactor, oneTimeTransactionViewModel);
    }

}
