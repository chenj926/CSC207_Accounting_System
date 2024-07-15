package use_case;

public interface LogoutOutputBoundary {
    void prepareSuccessView(LogoutOutputData user);

    void prepareFailView(String error);
}
