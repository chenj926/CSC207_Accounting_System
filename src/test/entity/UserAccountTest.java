package entity;

import java.util.ArrayList;

public class UserAccountTest {

    public static void main(String[] args) {
        UserAccount account = new UserAccount("testUser", "testPass", "testID");

        assert "testUser".equals(account.getUsername()) : "Username doesn't match";
        assert "testPass".equals(account.getPassword()) : "Password doesn't match";
        assert "testID".equals(account.getIdentification()) : "Identification doesn't match";
        assert account.getTotalIncome() == 0.0f : "Initial total income should be 0.0";
        assert account.getTotalOutflow() == 0.0f : "Initial total outflow should be 0.0";
        assert account.getTotalBalance() == 0.0f : "Initial total balance should be 0.0";
        assert account.getTransactions().isEmpty() : "Transactions should initially be empty";

        Transaction incomeTransaction = new Transaction(100.0f);
        Transaction outflowTransaction = new Transaction(-50.0f);

        account.addTransaction(incomeTransaction);
        account.addTransaction(outflowTransaction);

        assert account.getTotalIncome() == 100.0f : "Total income should be 100.0";
        assert account.getTotalOutflow() == -50.0f : "Total outflow should be -50.0";
        assert account.getTotalBalance() == 50.0f : "Total balance should be 50.0";

        ArrayList<Transaction> transactions = account.getTransactions();
        assert transactions.size() == 2 : "There should be 2 transactions";

        System.out.println("All tests passed.");
    }
}

class Transaction {
    private float amount;

    public Transaction(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }
}

class TransactionComparator implements java.util.Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return Float.compare(t1.getAmount(), t2.getAmount());
    }
}
