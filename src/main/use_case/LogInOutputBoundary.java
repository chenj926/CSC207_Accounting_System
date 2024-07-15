package use_case;


public interface SignInOutputBoundary {
    void prepareSuccessView(LogInOutputData user);

    void prepareFailView(String error);
}