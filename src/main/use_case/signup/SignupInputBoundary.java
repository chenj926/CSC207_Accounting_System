package use_case.signup;

public interface SignupInputBoundary<T> {
    void execute(T inputData);
}
