package use_case.homepage.shared_account;

import use_case.homepage.HomepageTwoOutBoundary;

public interface SharedAccountHomepageTwoOutputBoundary extends HomepageTwoOutBoundary<SharedAccountHomepageTwoOutputData> {

    void prepareSuccessView(SharedAccountHomepageTwoOutputData userAccountHomepageTwoOutputData);
}
