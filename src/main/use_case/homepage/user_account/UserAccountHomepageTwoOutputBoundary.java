package use_case.homepage.user_account;

import use_case.homepage.HomepageTwoOutBoundary;

public interface UserAccountHomepageTwoOutputBoundary extends HomepageTwoOutBoundary<UserAccountHomepageTwoOutputData> {

    void prepareSuccessView(UserAccountHomepageTwoOutputData userAccountHomepageTwoOutputData);
}
