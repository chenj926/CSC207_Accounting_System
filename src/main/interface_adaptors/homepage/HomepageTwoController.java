package interface_adaptors.homepage;

import use_case.homepage.HomepageTwoInputBoundary;
import use_case.homepage.HomepageTwoInputData;

public class HomepageTwoController {

    private final HomepageTwoInputBoundary homepageTwoInputBoundary;
    private final HomepageTwoViewModel viewModel;


    /**
     * Constructs a FinancialReportController with the specified interactor.
     *
     * @param homepageTwoInputBoundary the use case interactor for generating financial reports
     */
    public HomepageTwoController(HomepageTwoInputBoundary homepageTwoInputBoundary, HomepageTwoViewModel viewModel) {
        this.homepageTwoInputBoundary = homepageTwoInputBoundary;
        this.viewModel = viewModel;
    }

    /**
     * Generates a financial report for the specified account and date range.
     *
     //     * @param userName the account ID
     //     * @param startDate the start date of the report period
     //     * @param endDate the end date of the report period
     */
    public void execute(String identification) {
        HomepageTwoInputData inputData = new HomepageTwoInputData(identification);
        homepageTwoInputBoundary.execute(inputData);
    }

}
