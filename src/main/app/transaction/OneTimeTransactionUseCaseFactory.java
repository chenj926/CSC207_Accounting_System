package app.transaction;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import interface_adaptors.*;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionController;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionViewModel;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputBoundary;
import view.transaction.one_time.user_account.UserAccountOneTimeTransactionView;

import javax.swing.*;
import java.io.IOException;

public class OneTimeTransactionUseCaseFactory {
    private OneTimeTransactionUseCaseFactory() {}

    public static UserAccountOneTimeTransactionView create(ViewManagerModel viewManagerModel,
                                                           UserOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel) {
        try {
            UserOneTimeTransactionController userAccountOneTimeTransactionController = createUserOneTimeUseCase(viewManagerModel,
                    userAccountOneTimeTransactionViewModel);
            return new UserAccountOneTimeTransactionView(userAccountOneTimeTransactionViewModel, userAccountOneTimeTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static UserOneTimeTransactionController createUserOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                                             UserOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getOneTimeTransactionDAO();
        UserAccountOneTimeTransactionOutputBoundary presenter = new UserOneTimeTransactionPresenter(userAccountOneTimeTransactionViewModel, viewManagerModel);

        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        UserAccountOneTimeTransactionInteractor interactor = new UserAccountOneTimeTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new UserOneTimeTransactionController(interactor, userAccountOneTimeTransactionViewModel);
    }

}
