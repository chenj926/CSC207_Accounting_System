package use_case;

public interface SignupOutputBoundary {
    void prepareSuccessView(OneTimeTransactionOutputData transactions);

    void prepareFailView(String error);
}