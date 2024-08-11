package entity.account.user_account;

import entity.account.Account;

import java.util.HashSet;
import java.util.Set;

/**
 * The UserAccount class implements the Account interface.
 * This class represents a user account with details such as username, password, identification,
 * and a list of transactions. It also keeps track of the total income, total outflow, and total balance.
 *
 * @author Eric
 * @author Jessica
 */
public class UserAccount extends Account {
    private Set<String> sharedAccounts;
    /**
     * Constructs a UserAccount object with the specified username, password, and identification.
     * Initializes the transaction list and sets the income, outflow, and balance to zero.
     *
     * @param username       the username of the user account
     * @param password       the password of the user account
     * @param identification the identification of the user account
     */
    public UserAccount(String username, String password, String identification) {
        super(username, password, identification);
        this.sharedAccounts = new HashSet<>();
    }

    /**
     * Constructs a UserAccount object with the specified username, password, identification,
     * total income, total outflow, and total balance. Initializes the transaction list.
     *
     * @param username       the username of the user account
     * @param password       the password of the user account
     * @param identification the identification of the user account
     * @param totalIncome    the total cumulated income of the user account
     * @param totalOutflow   the total cumulated outflow of the user account
     * @param totalBalance   the total cumulated balance of the user account
     */
    public UserAccount(String username, String password, String identification,
                       float totalIncome, float totalOutflow, float totalBalance) {
        super(username, password, identification, totalIncome, totalOutflow, totalBalance);
        this.sharedAccounts = new HashSet<>();
    }

    public Set<String> getSharedAccounts() {
        return this.sharedAccounts;
    }

    public void setSharedAccounts(Set<String> sharedAccounts) {
        this.sharedAccounts = sharedAccounts;
    }

    public void addSharedAccount(String sharedAccountId) {
        this.sharedAccounts.add(sharedAccountId);
    }

    public void removeSharedAccount(String sharedAccountId) {
        this.sharedAccounts.remove(sharedAccountId);
    }
}