package use_case;


public interface SignInOutputBoundary {
    void prepareSuccessView(SignInOutputData user);

    void prepareFailView(String error);
}