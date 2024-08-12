package use_case.homepage.user_account;

import use_case.homepage.HomepageTwoInputBoundary;

public interface UserAccountHomepageTwoInputBoundary extends HomepageTwoInputBoundary<UserAccountHomepageTwoInputData> {

    void execute(UserAccountHomepageTwoInputData inputData);
}
