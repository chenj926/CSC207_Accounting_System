package interface_adaptors.homepage.shared_account;

import interface_adaptors.homepage.HomepageTwoController;
import use_case.homepage.shared_account.SharedAccountHomepageTwoInputBoundary;
import use_case.homepage.shared_account.SharedAccountHomepageTwoInputData;

public class SharedAccountHomepageTwoController extends HomepageTwoController<
        SharedAccountHomepageTwoInputBoundary,
        SharedAccountHomepageTwoViewModel,
        SharedAccountHomepageTwoInputData,
        SharedAccountHomepageTwoState> {

    public SharedAccountHomepageTwoController(SharedAccountHomepageTwoInputBoundary inputBoundary,
                                              SharedAccountHomepageTwoViewModel viewModel) {
        super(inputBoundary, viewModel);
    }

    @Override
    protected SharedAccountHomepageTwoInputData createInputData(String id) {
        return new SharedAccountHomepageTwoInputData(id);
    }
}
