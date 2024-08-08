package use_case.homepage;

public interface SharedAccountHomepageTwoOutputBoundary extends HomepageTwoOutBoundary<SharedAccountHomepageTwoOutputData>{

    void prepareSuccessView(SharedAccountHomepageTwoOutputData userAccountHomepageTwoOutputData);
}
