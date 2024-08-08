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

/**
 * Factory class for creating the Shared Account Homepage Two use case components.
 * <p>
 * This factory class is responsible for creating and assembling the components required
 * for the Shared Account Homepage Two use case, including the view, view model, interactor, and presenter.
 * </p>
 * @author Eric
 */
public class SharedAccountHomepageTwoUseCaseFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private SharedAccountHomepageTwoUseCaseFactory() {
    }

    /**
     * Creates and returns the Shared Account Homepage Two view.
     * <p>
     * This method creates the Shared Account Homepage Two view, sets up the necessary interactor and controller,
     * and handles any exceptions that may occur during the process.
     * </p>
     *
     * @param viewManagerModel the view manager model used to manage view transitions
     * @param viewModel        the view model to update the Shared Account Homepage Two state
     * @return the assembled Shared Account Homepage Two view
     */
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

    /**
     * Creates and returns the Shared Account Homepage Two controller.
     * <p>
     * This method creates the necessary interactor and presenter for the Shared Account Homepage Two use case
     * and assembles them into a controller to manage the user interactions.
     * </p>
     *
     * @param viewManagerModel the view manager model used to manage view transitions
     * @param viewModel        the view model to update the Shared Account Homepage Two state
     * @return the assembled Shared Account Homepage Two controller
     * @throws IOException if there is an error accessing the shared data file
     */
    private static SharedAccountHomepageTwoController createSharedAccountHomepageTwoUseCase(ViewManagerModel viewManagerModel,
                                                                                            SharedAccountHomepageTwoViewModel viewModel) throws IOException {
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getSharedAccountHomepageTwoDAO();
        SharedAccountHomepageTwoPresenter presenter = new SharedAccountHomepageTwoPresenter(viewModel, viewManagerModel);

        // Create the interactor for the shared account homepage two use case
        SharedAccountHomepageTwoInteractor interactor = new SharedAccountHomepageTwoInteractor(dataAccessObject, presenter);

        // Return the controller that handles shared account homepage two interactions
        return new SharedAccountHomepageTwoController(interactor, viewModel);
    }
}

