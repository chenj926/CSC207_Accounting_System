package interface_adaptors.homepage;

import interface_adaptors.ViewManagerModel;
import use_case.homepage.HomepageTwoOutputBoundary;
import use_case.homepage.HomepageTwoOutputData;

import java.util.Arrays;

public class HomepageTwoPresenter implements HomepageTwoOutputBoundary {
    private String[] basicUserInfo;
    private final HomepageTwoViewModel viewModel;
    private final ViewManagerModel viewManager;
//    private ViewManagerModel viewManager;

    public HomepageTwoPresenter(HomepageTwoViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Presents the financial report by storing and printing its content.
     *
     * @param outputData the output data containing the report content
     */
    @Override
    public void prepareSuccessView(HomepageTwoOutputData outputData) {
        HomepageTwoState state = this.viewModel.getState();
        this.basicUserInfo = outputData.getBasicUserInfo();

        // debug
        System.out.println("in presenter"+Arrays.toString(this.basicUserInfo));

        state.setBasicUserInfo(this.basicUserInfo);
        System.out.println("in presenter\nget from state"+Arrays.toString(viewModel.getState().getBasicUserInfo()));

        state.setErr(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setBasicUserInfo(state.getBasicUserInfo());
        this.viewManager.setBasicUserInfo(this.basicUserInfo);
        this.viewModel.firePropertyChanged();

//        this.viewManager.setActiveViewName(viewModel.getViewName());

    }

    // 如果user还没有transaction，就report说暂无transaction
    @Override
    public void prepareFailView(String err) {
        HomepageTwoState state = this.viewModel.getState();
//        state.setNoTransaction(noTransaction);
        this.viewModel.setState(state);
        this.viewModel.firePropertyChanged();
    }

    /**
     * Returns the report content.
     *
     * @return the report content
     */
    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }
}
