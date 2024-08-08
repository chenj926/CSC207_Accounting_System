package interface_adaptors.homepage;

import use_case.homepage.UserAccountHomepageTwoInputBoundary;
import use_case.homepage.UserAccountHomepageTwoInputData;

public class HomepageTwoController {

    private final UserAccountHomepageTwoInputBoundary userAccountHomepageTwoInputBoundary;
    private final HomepageTwoViewModel viewModel;


    /**
     * Constructs a FinancialReportController with the specified interactor.
     *
     * @param userAccountHomepageTwoInputBoundary the use case interactor for generating financial reports
     */
    public HomepageTwoController(UserAccountHomepageTwoInputBoundary userAccountHomepageTwoInputBoundary, HomepageTwoViewModel viewModel) {
        this.userAccountHomepageTwoInputBoundary = userAccountHomepageTwoInputBoundary;
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
        UserAccountHomepageTwoInputData inputData = new UserAccountHomepageTwoInputData(identification);
        userAccountHomepageTwoInputBoundary.execute(inputData);
    }

}
