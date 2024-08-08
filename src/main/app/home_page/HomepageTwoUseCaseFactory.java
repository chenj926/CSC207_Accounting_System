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

public class HomepageTwoUseCaseFactory {
    private HomepageTwoUseCaseFactory() {
    }

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

    private static UserAccountHomepageTwoController createHomepageTwoUseCase(ViewManagerModel viewManagerModel,
                                                                             UserAccountHomepageTwoViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getHomepageTwoDAO();
        UserAccountHomepageTwoPresenter presenter = new UserAccountHomepageTwoPresenter(viewModel, viewManagerModel);
//        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        UserAccountHomepageTwoInteractor interactor = new UserAccountHomepageTwoInteractor(dataAccessObject, presenter);

        // Return the controller that can handle both types of signups
        return new UserAccountHomepageTwoController(interactor, viewModel);
    }

}
