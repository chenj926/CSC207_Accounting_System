package use_case.homepage;

public interface HomepageTwoOutputBoundary {

    void prepareSuccessView(HomepageTwoOutputData homepageTwoOutputData);

    void prepareFailView(String err);
}
