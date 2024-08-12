package app.FinancialReport;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportController;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportPresenter;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import use_case.financial_report.user_account.UserAccountFinancialReportInteractor;
import use_case.financial_report.user_account.UserAccountFinancialReportOutputBoundary;
import view.financial_report.user_account.UserAccountFinancialReportView;

import javax.swing.*;
import java.io.IOException;

public class FinancialReportUseCaseFactory {

    private FinancialReportUseCaseFactory() {}

    public static UserAccountFinancialReportView create(ViewManagerModel viewManagerModel,
                                                        UserAccountFinancialReportViewModel viewModel) {
        try {
            UserAccountFinancialReportController userAccountFinancialReportController = createFinancialReportUseCase(viewManagerModel, viewModel);
            return new UserAccountFinancialReportView(viewModel, userAccountFinancialReportController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static UserAccountFinancialReportController createFinancialReportUseCase(ViewManagerModel viewManagerModel,
                                                                                     UserAccountFinancialReportViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getFinancialReportDAO();
        UserAccountFinancialReportOutputBoundary presenter = new UserAccountFinancialReportPresenter(viewModel, viewManagerModel);
        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        UserAccountFinancialReportInteractor interactor = new UserAccountFinancialReportInteractor(userAccount, presenter, dataAccessObject);

        // Return the controller that can handle both types of signups
        return new UserAccountFinancialReportController(interactor, viewModel);
    }
}
