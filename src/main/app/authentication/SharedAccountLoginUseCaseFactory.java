package app.authentication;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.authentication.shared_account.SharedAccountLoginDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.shared_account.SharedAccountLoginController;
import interface_adaptors.login.shared_account.SharedAccountLoginPresenter;
import interface_adaptors.login.shared_account.SharedAccountLoginViewModel;
import use_case.login.shared_account.SharedAccountLoginInteractor;
import use_case.login.shared_account.SharedAccountLoginMediator;
import use_case.login.shared_account.SharedAccountLoginOutputBoundary;
import use_case.update_periodic_at_login.shared_account.SharedAccountUpdatePeriodicAtLoginInteractor;
import view.login.SharedAccountLoginView;

import javax.swing.*;
import java.io.IOException;

public class SharedAccountLoginUseCaseFactory {

    public static SharedAccountLoginView create(ViewManagerModel viewManagerModel,
                                                SharedAccountLoginViewModel viewModel) {
        try {
            SharedAccountLoginController controller = createSharedAccountUserLoginUseCase(viewManagerModel, viewModel);
            return new SharedAccountLoginView(viewModel, controller, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SharedAccountLoginController createSharedAccountUserLoginUseCase(ViewManagerModel viewManagerModel,
                                                                                    SharedAccountLoginViewModel viewModel) throws IOException {
        SharedAccountLoginDataAccessInterface loginDataAccessObject = DAOFactory.getSharedAccountLoginDataAccessObject();
        SharedAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getShareAccountDataAccessObject();

        SharedAccountLoginOutputBoundary presenter = new SharedAccountLoginPresenter(viewManagerModel, viewModel);
        SharedAccountLoginInteractor interactor = new SharedAccountLoginInteractor(loginDataAccessObject, presenter);

        SharedAccountUpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new SharedAccountUpdatePeriodicAtLoginInteractor(periodicTransactionDataAccessObject);

        SharedAccountLoginMediator sharedAccountLoginMediator = new SharedAccountLoginMediator(interactor, updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        interactor.setMediator(sharedAccountLoginMediator);
        return new SharedAccountLoginController(sharedAccountLoginMediator);
    }
}
