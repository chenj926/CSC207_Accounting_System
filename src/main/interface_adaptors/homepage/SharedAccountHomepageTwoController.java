package interface_adaptors.homepage;

import use_case.homepage.*;

public class SharedAccountHomepageTwoController extends HomepageTwoController<
        SharedAccountHomepageTwoInputBoundary,
        SharedAccountHomepageTwoViewModel,
        SharedAccountHomepageTwoInputData,
        SharedAccountHomepageTwoState>  {

    public SharedAccountHomepageTwoController(SharedAccountHomepageTwoInputBoundary inputBoundary,
                                              SharedAccountHomepageTwoViewModel viewModel) {
        super(inputBoundary, viewModel);
    }

    @Override
    protected SharedAccountHomepageTwoInputData createInputData(String id) {
        return new SharedAccountHomepageTwoInputData(id);
    }
}
