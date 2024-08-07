package use_case.signup;

public interface SignupOutputBoundary<T> {

    void prepareSuccessView(T user);

    void prepareFailView(String error);
}
