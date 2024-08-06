package app.transaction;

import data_access.DAOFactory;
import data_access.account.ShareAccountDataAccessInterface;
import entity.account.SharedAccount;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.SharedAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.SharedAccountOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.SharedAccountOneTimeTransactionViewModel;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputBoundary;
import view.transaction.one_time.SharedAccountOneTimeTransactionView;

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
        ShareAccountDataAccessInterface dataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountOneTimeTransactionOutputBoundary presenter = new SharedAccountOneTimeTransactionPresenter(oneTimeTransactionViewModel, viewManagerModel);
        SharedAccount sharedAccount = dataAccessObject.getById(viewManagerModel.getUserId());
        SharedAccountOneTimeTransactionInteractor interactor = new SharedAccountOneTimeTransactionInteractor(dataAccessObject, presenter, sharedAccount);
        return new SharedAccountOneTimeTransactionController(interactor, oneTimeTransactionViewModel);
    }

}



