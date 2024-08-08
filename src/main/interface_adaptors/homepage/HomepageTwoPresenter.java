package interface_adaptors.homepage;

import interface_adaptors.ViewManagerModel;
import use_case.homepage.HomepageTwoOutBoundary;
import use_case.homepage.HomepageTwoOutputData;

public abstract class HomepageTwoPresenter<
        O extends HomepageTwoOutputData,
        V extends HomepageTwoViewModel<S>,
        S extends HomepageTwoState> implements HomepageTwoOutBoundary<O> {

    protected String[] basicUserInfo;
    protected final V viewModel;
    protected final ViewManagerModel viewManager;

    public HomepageTwoPresenter(V viewModel, ViewManagerModel viewManager){
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareSuccessView(O outputData) {
        S state = this.viewModel.getState();
        this.basicUserInfo = outputData.getBasicUserInfo();

        state.setBasicUserInfo(this.basicUserInfo);
        state.setErr(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setBasicUserInfo(state.getBasicUserInfo());
        this.viewManager.setBasicUserInfo(this.basicUserInfo);
        this.viewModel.firePropertyChanged();
    }

    /**
     * Prepares the view to display a failure message by updating the state with the error.
     *
     * @param err the error message to be displayed
     */
    @Override
    public void prepareFailView(String err) {
        S state = (S) this.viewModel.getState();
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
