package app.FinancialReport;

public class SharedAccountFinancialReportUseCaseFactory {
    private SharedAccountFinancialReportUseCaseFactory() {}

    // ia很多没写所以红了，先comment掉

//    public static SharedAccountFinancialReportView create(ViewManagerModel viewManagerModel,
//                                                          SharedAccountFinancialReportViewModel viewModel) {
//        try {
//            SharedAccountFinancialReportController controller = createFinancialReportUseCase(viewManagerModel, viewModel);
//            return new FinancialReportView(viewModel, controller, viewManagerModel);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open user data file.");
//        }
//        return null;
//    }
//
//    private static SharedAccountFinancialReportController createSharedAccountFinancialReportUseCase(ViewManagerModel viewManagerModel,
//                                                                          FinancialReportViewModel viewModel) throws IOException {
//        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getSharedAccountFinancialReportDAO();
//        SharedAccountFinancialReportOutputBoundary presenter = new SharedAccountAccountFinancialReportPresenter(viewModel, viewManagerModel);
//        SharedAccount account = dataAccessObject.getById(viewManagerModel.getUserId());
//
//        // Create the interactors for standard and shared account signups
//        SharedAccountFinancialReportInteractor interactor = new SharedAccountFinancialReportInteractor(account, presenter, dataAccessObject);
//
//        // Return the controller that can handle both types of signups
//        return new SharedAccountFinancialReportController(interactor, viewModel);
//    }
}
