package interface_adaptors.homepage;

import interface_adaptors.ViewManagerModel;
import use_case.homepage.HomepageTwoOutBoundary;
import use_case.homepage.HomepageTwoOutputData;

public class HomepageTwoPresenter<
        O extends HomepageTwoOutputData,
        V extends HomepageTwoViewModel,
        S extends HomepageTwoState> implements HomepageTwoOutBoundary<O> {
    protected String[] basicUserInfo;
    protected final V viewModel;
    protected final ViewManagerModel viewManager;

    public HomepageTwoPresenter(V viewModel, ViewManagerModel viewManager){
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }
}
