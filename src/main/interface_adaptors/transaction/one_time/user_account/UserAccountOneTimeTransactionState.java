package interface_adaptors.transaction.one_time.user_account;

import interface_adaptors.transaction.one_time.AccountOneTimeTransactionState;

/**
 * The UserAccountOneTimeTransactionState class represents the state of a one-time transaction,
 * including transaction details, new balance, and success or error messages.
 *
 * @author Xile
 * @author Eric
 */
public class UserAccountOneTimeTransactionState extends AccountOneTimeTransactionState {

    /**
     * Constructs a UserAccountOneTimeTransactionState object with default values.
     */
    public UserAccountOneTimeTransactionState() {
        super();
    }
}
