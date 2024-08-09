package app.FinancialReport;

import data_access.DAOFactory;
import data_access.account.SharedAccountDataAccessInterface;
import entity.account.SharedAccount;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.FinancialReportViewModel;
import interface_adaptors.financial_report.SharedAccountFinancialReportController;
import interface_adaptors.financial_report.SharedAccountFinancialReportPresenter;
import interface_adaptors.financial_report.SharedAccountFinancialReportViewModel;
import use_case.financial_report.SharedAccountFinancialReportInteractor;
import use_case.financial_report.SharedAccountFinancialReportOutputBoundary;
import view.financial_report.FinancialReportView;
import view.financial_report.SharedAccountFinancialReportView;

import javax.swing.*;
import java.io.IOException;


public class SharedAccountFinancialReportUseCaseFactory {
    private SharedAccountFinancialReportUseCaseFactory() {}

    // ia很多没写所以红了，先comment掉

    public static SharedAccountFinancialReportView create(ViewManagerModel viewManager,
                                                          SharedAccountFinancialReportViewModel viewModel) {
        try {
            SharedAccountFinancialReportController controller = createSharedAccountFinancialReportUseCase(viewManager, viewModel);
            return new SharedAccountFinancialReportView(viewModel, controller, viewManager);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SharedAccountFinancialReportController createSharedAccountFinancialReportUseCase(ViewManagerModel viewManagerModel,
                                                                          SharedAccountFinancialReportViewModel viewModel) throws IOException {
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getSharedAccountFinancialReportDAO();
        SharedAccountFinancialReportOutputBoundary presenter = new SharedAccountFinancialReportPresenter(viewModel, viewManagerModel);
        SharedAccount account = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        SharedAccountFinancialReportInteractor interactor = new SharedAccountFinancialReportInteractor(account, presenter, dataAccessObject);

        // Return the controller that can handle both types of signups
        return new SharedAccountFinancialReportController(interactor, viewModel);
    }
}
