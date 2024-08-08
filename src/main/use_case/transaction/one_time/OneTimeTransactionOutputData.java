package use_case.transaction.one_time;

import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.TransactionOutputData;

public class OneTimeTransactionOutputData extends TransactionOutputData<Transaction> {

    public OneTimeTransactionOutputData(OneTimeTransaction onetimeTransaction) {
        super(onetimeTransaction);
    }
}
