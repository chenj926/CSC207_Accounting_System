package use_case;


public interface LogInOutputBoundary {
    void prepareSuccessView(LogInOutputData user);

    void prepareFailView(String error);
}