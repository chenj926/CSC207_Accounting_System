package app.FinancialReport;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.AccountFactory;
import entity.account.UserAccount;
import interface_adaptors.FinancialReport.FinancialReportController;
import interface_adaptors.FinancialReport.FinancialReportPresenter;
import interface_adaptors.FinancialReport.FinancialReportViewModel;
import interface_adaptors.HomePageViewModel;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SignupController;
import interface_adaptors.signup.SignupPresenter;
import interface_adaptors.signup.SignupViewModel;
import use_case.FinancialReport.FinancialReportInteractor;
import use_case.FinancialReport.FinancialReportOutputBoundary;
import use_case.signup.SharedAccountSignupInteractor;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.FinancialReport.FinancialReportView;
import view.home_page.HomePageView;
import view.signup.SignupView;

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
