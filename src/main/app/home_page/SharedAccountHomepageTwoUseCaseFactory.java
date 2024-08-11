package app.home_page;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoController;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoPresenter;
import interface_adaptors.homepage.shared_account.SharedAccountHomepageTwoViewModel;
import use_case.homepage.shared_account.SharedAccountHomepageTwoInteractor;
import view.home_page.SharedAccountHomepageTwoView;

import javax.swing.*;
import java.io.IOException;

public class SharedAccountHomepageTwoUseCaseFactory {

    private SharedAccountHomepageTwoUseCaseFactory() {
    }

    public static SharedAccountHomepageTwoView create(ViewManagerModel viewManagerModel,
                                                      SharedAccountHomepageTwoViewModel viewModel) {
        try {
            SharedAccountHomepageTwoController sharedAccountHomepageTwoController = createSharedAccountHomepageTwoUseCase(viewManagerModel, viewModel);
            return new SharedAccountHomepageTwoView(viewModel, viewManagerModel, sharedAccountHomepageTwoController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open shared data file.");
        }
        return null;
    }

    private static SharedAccountHomepageTwoController createSharedAccountHomepageTwoUseCase(ViewManagerModel viewManagerModel,
                                                                             SharedAccountHomepageTwoViewModel viewModel) throws IOException {
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getSharedAccountHomepageTwoDAO();
        SharedAccountHomepageTwoPresenter presenter = new SharedAccountHomepageTwoPresenter(viewModel, viewManagerModel);

        // Create the interactors for standard and shared account signups
        SharedAccountHomepageTwoInteractor interactor = new SharedAccountHomepageTwoInteractor(dataAccessObject, presenter);

        // Return the controller that can handle both types of signups
        return new SharedAccountHomepageTwoController(interactor, viewModel);
    }
}
