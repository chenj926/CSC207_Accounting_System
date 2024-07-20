package app;

import data_access.DAOFactory;
import data_access.LoginDataAccessInterface;
import entity.AccountFactory;
import interface_adaptors.*;
import use_case.LoginInteractor;
import use_case.LoginOutputBoundary;
import use_case.OneTimeTransactionInteractor;
import use_case.OneTimeTransactionOutputBoundary;
import view.*;

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
        OneTimeTransactionDataAccessInterface dataAccessObject = DAOFactory.getLoginDataAccessObject();
        AccountFactory accountFactory = new AccountFactory();
        OneTimeTransactionOutputBoundary presenter = new OneTimeTransactionPresenter(oneTimeTransactionViewModel, viewManagerModel);
        OneTimeTransactionInteractor interactor = new LoginInteractor(dataAccessObject, presenter, accountFactory);
        return new OneTimeTransactionController(interactor);
    }

}
