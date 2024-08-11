package interface_adaptors.homepage.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.HomepageTwoPresenter;
import use_case.homepage.shared_account.SharedAccountHomepageTwoOutputBoundary;
import use_case.homepage.shared_account.SharedAccountHomepageTwoOutputData;

public class SharedAccountHomepageTwoPresenter extends HomepageTwoPresenter<
        SharedAccountHomepageTwoOutputData,
        SharedAccountHomepageTwoViewModel,
        SharedAccountHomepageTwoState> implements SharedAccountHomepageTwoOutputBoundary {

    public SharedAccountHomepageTwoPresenter(SharedAccountHomepageTwoViewModel viewModel, ViewManagerModel viewManagerModel){
        super(viewModel, viewManagerModel);
    }
}
