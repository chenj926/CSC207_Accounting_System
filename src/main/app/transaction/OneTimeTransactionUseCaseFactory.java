package app.transaction;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import interface_adaptors.*;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionViewModel;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputBoundary;
import view.transaction.one_time.OneTimeTransactionView;

import javax.swing.*;
import java.io.IOException;

public class OneTimeTransactionUseCaseFactory {
    private OneTimeTransactionUseCaseFactory() {}

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

    private static UserAccountOneTimeTransactionController createUserOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                                                    UserAccountOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getOneTimeTransactionDAO();
        UserAccountOneTimeTransactionOutputBoundary presenter = new UserAccountOneTimeTransactionPresenter(userAccountOneTimeTransactionViewModel, viewManagerModel);

        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        UserAccountOneTimeTransactionInteractor interactor = new UserAccountOneTimeTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new UserAccountOneTimeTransactionController(interactor, userAccountOneTimeTransactionViewModel);
    }

}
