package use_case.homepage;

public interface SharedAccountHomepageTwoInputBoundary extends HomepageTwoInputBoundary<SharedAccountHomepageTwoInputData>{
    void execute(SharedAccountHomepageTwoInputData inputData);
}
