package use_case.homepage.shared_account;

import use_case.homepage.HomepageTwoInputBoundary;

public interface SharedAccountHomepageTwoInputBoundary extends HomepageTwoInputBoundary<SharedAccountHomepageTwoInputData> {
    void execute(SharedAccountHomepageTwoInputData inputData);
}
