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

public class SharedAccountOneTimeTransactionUseCaseFactory {
    private SharedAccountOneTimeTransactionUseCaseFactory() {}

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

    private static SharedAccountOneTimeTransactionController createSharedAccountOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                                         SharedAccountOneTimeTransactionViewModel oneTimeTransactionViewModel) throws IOException {
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountOneTimeTransactionOutputBoundary presenter = new SharedAccountOneTimeTransactionPresenter(oneTimeTransactionViewModel, viewManagerModel);
        SharedAccount sharedAccount = dataAccessObject.getById(viewManagerModel.getUserId());
        SharedAccountOneTimeTransactionInteractor interactor = new SharedAccountOneTimeTransactionInteractor(dataAccessObject, presenter, sharedAccount);
        return new SharedAccountOneTimeTransactionController(interactor, oneTimeTransactionViewModel);
    }

}



