package app.FinancialReport;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import interface_adaptors.financial_report.UserAccountFinancialReportController;
import interface_adaptors.financial_report.UserAccountFinancialReportPresenter;
import interface_adaptors.financial_report.UserAccountFinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import use_case.financial_report.UserAccountFinancialReportInteractor;
import use_case.financial_report.UserAccountFinancialReportOutputBoundary;
import view.financial_report.FinancialReportView;

import javax.swing.*;
import java.io.IOException;

/**
 * The FinancialReportUseCaseFactory class is responsible for creating instances of FinancialReportView.
 * It also creates the necessary components for handling user account financial report use cases.
 * This class ensures the proper initialization of the financial report controllers and their dependencies.
 * @author Dna
 * @author ChiFongHuang
 * @author Jessica
 */
public class FinancialReportUseCaseFactory {

    private FinancialReportUseCaseFactory() {}

    /**
     * Creates and returns an instance of FinancialReportView for user account financial report.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param viewModel the view model to update the financial report state
     * @return an instance of FinancialReportView
     */
    public static FinancialReportView create(ViewManagerModel viewManagerModel,
                                             UserAccountFinancialReportViewModel viewModel) {
        try {
            UserAccountFinancialReportController userAccountFinancialReportController = createFinancialReportUseCase(viewManagerModel, viewModel);
            return new FinancialReportView(viewModel, userAccountFinancialReportController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and returns an instance of UserAccountFinancialReportController for user account financial report.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param viewModel the view model to update the financial report state
     * @return an instance of UserAccountFinancialReportController
     * @throws IOException if an I/O error occurs
     */
    private static UserAccountFinancialReportController createFinancialReportUseCase(ViewManagerModel viewManagerModel,
                                                                                     UserAccountFinancialReportViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getFinancialReportDAO();
        UserAccountFinancialReportOutputBoundary presenter = new UserAccountFinancialReportPresenter(viewModel, viewManagerModel);
        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactor for user account financial report
        UserAccountFinancialReportInteractor interactor = new UserAccountFinancialReportInteractor(userAccount, presenter, dataAccessObject);

        // Return the controller for user account financial report
        return new UserAccountFinancialReportController(interactor, viewModel);
    }
}

