package app.home_page;

import data_access.DAOFactory;
import data_access.account.SharedAccountDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.SharedAccountHomepageTwoController;
import interface_adaptors.homepage.SharedAccountHomepageTwoPresenter;
import interface_adaptors.homepage.SharedAccountHomepageTwoViewModel;
import use_case.homepage.SharedAccountHomepageTwoInteractor;
import view.home_page.HomepageTwoView;
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
