package use_case.login;

public interface LoginInputBoundary<T> {
    void execute(T inputData);

}
