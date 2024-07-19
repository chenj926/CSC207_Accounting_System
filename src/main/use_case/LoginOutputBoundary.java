package use_case;


public interface LoginOutputBoundary {
    void prepareSuccessView(LogInOutputData user);

    void prepareFailView(String error);
}