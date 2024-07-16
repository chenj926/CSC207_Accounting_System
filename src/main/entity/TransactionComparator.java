package entity;

import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        /* date comparator
         * return negative number if t1 is before t2
         * return zero if t1 equals t2
         * return positive number if t1 is after t2
         */
        int dateComparison = t1.getDate().compareTo(t2.getDate());
        if (dateComparison != 0) {
            return dateComparison;
        }

        /* if dates are the same, compare by amount
         * return negative number if t1 less than t2
         * return zero if t1 equals t2
         * return positive number if t1 greater t2
         */
        int amountComparison = Double.compare(t1.getAmount(), t2.getAmount());
        if (amountComparison != 0) {
            return amountComparison;
        }

        // If amounts are the same, compare by ID
        return t1.getIdentification().compareTo(t2.getIdentification());
    }
}