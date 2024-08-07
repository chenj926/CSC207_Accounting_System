package use_case.login;

public interface LoginOutputBoundary<T> {

    void prepareSuccessView(T user);

    void prepareFailView(String error);
}
