package interface_adaptors.homepage;

import use_case.homepage.HomepageTwoInputBoundary;
import use_case.homepage.HomepageTwoInputData;

public abstract class HomepageTwoController<
        IB extends HomepageTwoInputBoundary<I>,
        V extends HomepageTwoViewModel<S>,
        I extends HomepageTwoInputData,
        S extends HomepageTwoState> {
    protected final IB inputBoundary;
    protected final V viewModel;

    protected HomepageTwoController(IB inputBoundary, V viewModel) {
        this.inputBoundary = inputBoundary;
        this.viewModel = viewModel;
    }

    public void execute(String id) {
        I inputData = this.createInputData(id);
        this.inputBoundary.execute(inputData);
    }

    protected abstract I createInputData(String id);
}
