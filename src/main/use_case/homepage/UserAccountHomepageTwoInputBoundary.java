package use_case.homepage;

public interface UserAccountHomepageTwoInputBoundary extends HomepageTwoInputBoundary<UserAccountHomepageTwoInputData> {

    void execute(UserAccountHomepageTwoInputData inputData);
}
