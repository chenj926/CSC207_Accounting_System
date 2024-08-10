package use_case.login;

public interface LoginOutputBoundary<O> {

    void prepareSuccessView(O user);

    void prepareFailView(String error);
}
