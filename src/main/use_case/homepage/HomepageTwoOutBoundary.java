package use_case.homepage;

public interface HomepageTwoOutBoundary<O> {
    void prepareSuccessView(O userAccountHomepageTwoOutputData);

    void prepareFailView(String err);
}
