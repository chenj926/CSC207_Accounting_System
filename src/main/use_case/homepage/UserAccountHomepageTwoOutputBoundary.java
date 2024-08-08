package use_case.homepage;

public interface UserAccountHomepageTwoOutputBoundary extends HomepageTwoOutBoundary<UserAccountHomepageTwoOutputData> {

    void prepareSuccessView(UserAccountHomepageTwoOutputData userAccountHomepageTwoOutputData);
}
