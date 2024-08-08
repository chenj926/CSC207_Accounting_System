package interface_adaptors.homepage;

import interface_adaptors.ViewManagerModel;
import use_case.homepage.SharedAccountHomepageTwoOutputBoundary;
import use_case.homepage.SharedAccountHomepageTwoOutputData;
import use_case.homepage.SharedAccountHomepageTwoOutputData;

public class SharedAccountHomepageTwoPresenter extends HomepageTwoPresenter <
        SharedAccountHomepageTwoOutputData,
        SharedAccountHomepageTwoViewModel,
        SharedAccountHomepageTwoState> implements SharedAccountHomepageTwoOutputBoundary {

    public SharedAccountHomepageTwoPresenter(SharedAccountHomepageTwoViewModel viewModel, ViewManagerModel viewManagerModel){
        super(viewModel, viewManagerModel);
    }
}
