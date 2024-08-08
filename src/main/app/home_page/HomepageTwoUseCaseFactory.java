package app.home_page;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.UserAccountHomepageTwoController;
import interface_adaptors.homepage.UserAccountHomepageTwoPresenter;
import interface_adaptors.homepage.UserAccountHomepageTwoViewModel;
import use_case.homepage.UserAccountHomepageTwoInteractor;
import view.home_page.HomepageTwoView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for creating the Homepage Two use case components.
 * <p>
 * This factory class is responsible for creating and assembling the components required
 * for the Homepage Two use case, including the view, controller, presenter, and interactor.
 * </p>
 * @author Eric
 */
public class HomepageTwoUseCaseFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private HomepageTwoUseCaseFactory() {
    }

    /**
     * Creates and returns the Homepage Two view.
     * <p>
     * This method creates the necessary components for the Homepage Two view,
     * including the controller and presenter, and returns the assembled view.
     * </p>
     *
     * @param viewManagerModel the view manager model used to manage view transitions
     * @param viewModel        the view model to update the Homepage Two state
     * @return the assembled Homepage Two view
     */
    public static HomepageTwoView create(ViewManagerModel viewManagerModel,
                                         UserAccountHomepageTwoViewModel viewModel) {
        try {
            UserAccountHomepageTwoController userAccountHomepageTwoController = createHomepageTwoUseCase(viewManagerModel, viewModel);
            return new HomepageTwoView(viewModel, viewManagerModel, userAccountHomepageTwoController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and returns the Homepage Two controller.
     * <p>
     * This method creates the necessary components for the Homepage Two use case,
     * including the data access object, presenter, and interactor, and returns the controller.
     * </p>
     *
     * @param viewManagerModel the view manager model used to manage view transitions
     * @param viewModel        the view model to update the Homepage Two state
     * @return the assembled Homepage Two controller
     * @throws IOException if an error occurs while accessing the data
     */
    private static UserAccountHomepageTwoController createHomepageTwoUseCase(ViewManagerModel viewManagerModel,
                                                                             UserAccountHomepageTwoViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getHomepageTwoDAO();
        UserAccountHomepageTwoPresenter presenter = new UserAccountHomepageTwoPresenter(viewModel, viewManagerModel);
//        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactor for the Homepage Two use case
        UserAccountHomepageTwoInteractor interactor = new UserAccountHomepageTwoInteractor(dataAccessObject, presenter);

        // Return the controller for the Homepage Two use case
        return new UserAccountHomepageTwoController(interactor, viewModel);
    }

}

