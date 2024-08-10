package interface_adaptors.homepage;

import use_case.homepage.HomepageTwoInputData;
import use_case.homepage.UserAccountHomepageTwoInputBoundary;
import use_case.homepage.UserAccountHomepageTwoInputData;

public class UserAccountHomepageTwoController extends HomepageTwoController<
        UserAccountHomepageTwoInputBoundary,
        UserAccountHomepageTwoViewModel,
        UserAccountHomepageTwoInputData,
        UserAccountHomepageTwoState>{


    /**
     * Constructs a FinancialReportController with the specified interactor.
     *
     * @param userAccountHomepageTwoInputBoundary the use case interactor for generating financial reports
     */
    public UserAccountHomepageTwoController(UserAccountHomepageTwoInputBoundary userAccountHomepageTwoInputBoundary, UserAccountHomepageTwoViewModel viewModel) {
        super(userAccountHomepageTwoInputBoundary, viewModel);
    }

    @Override
    protected UserAccountHomepageTwoInputData createInputData(String id) {
        return new UserAccountHomepageTwoInputData(id);
    }

}
