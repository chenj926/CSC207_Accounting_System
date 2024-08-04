package app.FinancialReport;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import interface_adaptors.financial_report.FinancialReportController;
import interface_adaptors.financial_report.FinancialReportPresenter;
import interface_adaptors.financial_report.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import use_case.financial_report.FinancialReportInteractor;
import use_case.financial_report.FinancialReportOutputBoundary;
import view.financial_report.FinancialReportView;

import javax.swing.*;
import java.io.IOException;

public class FinancialReportUseCaseFactory {

    private FinancialReportUseCaseFactory() {}

    public static FinancialReportView create(ViewManagerModel viewManagerModel,
                                             FinancialReportViewModel viewModel) {
        try {
            FinancialReportController financialReportController = createFinancialReportUseCase(viewManagerModel, viewModel);
            return new FinancialReportView(viewModel, financialReportController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static FinancialReportController createFinancialReportUseCase(ViewManagerModel viewManagerModel,
                                                                          FinancialReportViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getFinancialReportDAO();
        FinancialReportOutputBoundary presenter = new FinancialReportPresenter(viewModel, viewManagerModel);
        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        FinancialReportInteractor interactor = new FinancialReportInteractor(userAccount, presenter, dataAccessObject);

        // Return the controller that can handle both types of signups
        return new FinancialReportController(interactor, viewModel);
    }
}
