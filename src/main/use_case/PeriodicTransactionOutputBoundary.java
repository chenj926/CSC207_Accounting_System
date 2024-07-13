package use_case;

import java.util.*;

public interface PeriodicTransactionOutputBoundary {
    void prepareSuccessView(PeriodicTransactionOutputData transactions);

    void prepateFailVeiw(String err);
}