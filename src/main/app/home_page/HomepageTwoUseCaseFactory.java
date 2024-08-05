package app.home_page;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import interface_adaptors.FinancialReport.FinancialReportController;
import interface_adaptors.FinancialReport.FinancialReportPresenter;
import interface_adaptors.FinancialReport.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.HomepageTwoController;
import interface_adaptors.homepage.HomepageTwoPresenter;
import interface_adaptors.homepage.HomepageTwoViewModel;
import interface_adaptors.transaction.TransactionViewModel;
import use_case.FinancialReport.FinancialReportInteractor;
import use_case.FinancialReport.FinancialReportOutputBoundary;
import use_case.homepage.HomepageTwoInteractor;
import view.FinancialReport.FinancialReportView;
import view.home_page.HomepageTwoView;

import javax.swing.*;
import java.io.IOException;

public class HomepageTwoUseCaseFactory {
    private HomepageTwoUseCaseFactory() {
    }

//    public static HomepageTwoView create(ViewManagerModel viewManagerModel, HomepageTwoViewModel homepageTwoViewModel) {
//        HomepageTwoView homepageTwoView = new HomepageTwoView(homepageTwoViewModel, viewManagerModel);
//
//        homepageTwoView.addPropertyChangeListener(evt -> viewManagerModel.changeView("One Time Transaction"));
//        homepageTwoView.addPropertyChangeListener(evt -> viewManagerModel.changeView("Periodic Transaction"));
//        homepageTwoView.addPropertyChangeListener(evt -> viewManagerModel.changeView("Homepage Two"));
//
//
//        return homepageTwoView;
//    }

    public static HomepageTwoView create(ViewManagerModel viewManagerModel,
                                             HomepageTwoViewModel viewModel) {
        try {
            HomepageTwoController homepageTwoController = createHomepageTwoUseCase(viewManagerModel, viewModel);
            return new HomepageTwoView(viewModel, viewManagerModel, homepageTwoController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static HomepageTwoController createHomepageTwoUseCase(ViewManagerModel viewManagerModel,
                                                                          HomepageTwoViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getHomepageTwoDAO();
        HomepageTwoPresenter presenter = new HomepageTwoPresenter(viewModel, viewManagerModel);
//        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        HomepageTwoInteractor interactor = new HomepageTwoInteractor(dataAccessObject, presenter);

        // Return the controller that can handle both types of signups
        return new HomepageTwoController(interactor, viewModel);
    }

}
