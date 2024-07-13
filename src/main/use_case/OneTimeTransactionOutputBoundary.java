package use_case;

public interface OneTimeTransactionOutputBoundary{
    void prepareSuccessView(OneTimeTransactionOutputData transactions);

    void prepareFailView(String error);
}