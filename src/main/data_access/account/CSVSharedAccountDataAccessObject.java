package data_access.account;

import data_access.authentication.SharedAccountSignupDataAccessInterface;
import data_access.iterator.SharedAccountIterator;
import data_access.iterator.TransactionIterator;
import entity.account.SharedAccount;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputData;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDate;

import static java.lang.String.valueOf;

/**
 * A CSV-based implementation of data access for shared accounts.
 * <p>
 * This class extends {@link CSVUserAccountDataAccessObject} and implements {@link SharedAccountDataAccessInterface}.
 * It provides methods to manage shared accounts, including saving, updating, deleting, and retrieving shared account
 * information from CSV files.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class CSVSharedAccountDataAccessObject extends CSVAccountDataAccessObject<
        SharedAccount,
        SharedAccountOneTimeTransactionOutputData,
        SharedAccountPeriodicTransactionOutputData>
        implements SharedAccountDataAccessInterface,
        SharedAccountSignupDataAccessInterface {
    private static final String SHARED_ACCOUNT_CSV_FILE_PATH = "src/main/data/sharedAccounts.csv";
//    private static final String SHARED_ACCOUNT_USERS_CSV_FILE_PATH = "src/main/data/sharedAccountUsers.csv";
    private static final String SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH = "src/main/data/sharedAccountTransactions.csv";
    private static final String CSV_HEADER  = "sharedId,ids,password,totalIncome,totalOutflow,totalBalance,lastLoginDate";
    private static final String TRANSACTION_HEADER  = "id,amount,date,description,category,start date,period,end date";

    public CSVSharedAccountDataAccessObject() {
        super(SHARED_ACCOUNT_CSV_FILE_PATH, SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH, CSV_HEADER, TRANSACTION_HEADER);
    }

    /**
     * Checks if a shared account exists by its identification.
     * <p>
     * This method retrieves the shared account by its identification from the CSV file.
     * </p>
     *
     * @param sharedAccountIdentification the identification of the shared account
     * @return {@code true} if the shared account exists; {@code false} otherwise
     */
    @Override
    public boolean existById(String sharedAccountIdentification) {
        boolean userExist = false;
        try (SharedAccountIterator iterator = new SharedAccountIterator(accountCsvPath)) {
            while (iterator.hasNext()) {
                SharedAccount account = iterator.next();
                if (account.getIdentification().equals(sharedAccountIdentification)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existByuserId(String userId) {
        boolean userExist = false;
        String baseDir = System.getProperty("user.dir");
        Path tempCsvPath = Paths.get(baseDir, "src/main/data/accounts/userAccounts.csv");
        try (SharedAccountIterator iterator = new SharedAccountIterator(tempCsvPath)) {
            while (iterator.hasNext()) {
                UserAccount userAccount = iterator.next();
                if (userAccount.getIdentification().equals(userId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Saves a new shared account to the CSV file.
     * <p>
     * This method reads the existing shared accounts from the CSV file, adds the new shared account,
     * and writes all shared accounts back to the file.
     * </p>
     *
     * @param newSharedAccount the {@link SharedAccount} to be saved
     */
    @Override
    public void save(SharedAccount newSharedAccount) {
        if (!existById(newSharedAccount.getIdentification())) {
            String userInfo = getSharedAccountInfo(newSharedAccount);
            // if csv not created, create it
            confirmCsvExistence(this.accountCsvPath, userInfo);
        }
    }

    private static String getSharedAccountInfo(SharedAccount newSharedAccount) {
        // user info
        String id = newSharedAccount.getIdentification();
        Set<String> userIds = newSharedAccount.getSharedUserIdentifications();
        String stringUserIds = String.join(";", userIds);
        String password = newSharedAccount.getPassword();
        float totalIncome = newSharedAccount.getTotalIncome();
        float totalOutflow = newSharedAccount.getTotalOutflow();
        float totalBalance = newSharedAccount.getTotalBalance();
        LocalDate lastLoginDate = newSharedAccount.getLastLoginDate();
        String stringLastLoginDate = valueOf(lastLoginDate);

        // create csv line with the user info
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s", id, stringUserIds, password,
                totalIncome, totalOutflow, totalBalance, stringLastLoginDate);
    }

    /**
     * Deletes a shared account by its identification.
     * <p>
     * This method reads the existing shared accounts from the CSV file, removes the specified shared account,
     * and writes the updated list of shared accounts back to the file.
     * </p>
     *
     * @param sharedAccountIdentification the identification of the shared account to be deleted
     */
    @Override
    public void deleteById(String sharedAccountIdentification) {
        List<String> lines = new ArrayList<>();

        // Read all lines from the CSV file
        try (SharedAccountIterator iterator = new SharedAccountIterator(accountCsvPath)) {
            while (iterator.hasNext()) {
                SharedAccount sharedAccount = iterator.next();
                if (!sharedAccount.getIdentification().equals(sharedAccountIdentification)) {
                    lines.add(String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s",
                            sharedAccount.getIdentification(),
                            sharedAccount.getSharedUserIdentifications(),
                            sharedAccount.getPassword(),
                            sharedAccount.getTotalIncome(),
                            sharedAccount.getTotalOutflow(),
                            sharedAccount.getTotalBalance(),
                            sharedAccount.getLastLoginDate() != null ? sharedAccount.getLastLoginDate() : ""));
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Write all lines back to the CSV file, excluding the deleted user
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH), StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(CSV_HEADER);
            writer.newLine();
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Retrieves a shared account by its identification.
     * <p>
     * This method reads all shared accounts from the CSV file and returns the shared account that matches the given
     * identification.
     * </p>
     *
     * @param sharedAccountIdentification the identification of the shared account to retrieve
     * @return the {@link SharedAccount} with the given identification, or {@code null} if not found
     */
    @Override
    public SharedAccount getById(String sharedAccountIdentification) {
        SharedAccount shared = null;
        try (SharedAccountIterator iterator = new SharedAccountIterator(accountCsvPath)) {
            while (iterator.hasNext()) {
                SharedAccount sharedAccount = iterator.next();
                shared = sharedAccount;
                if (sharedAccount.getIdentification().equals(sharedAccountIdentification)) {
                    return sharedAccount;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return shared;
    }

    /**
     * Reads the transactions associated with a shared account from the CSV file.
     * <p>
     * This method parses the CSV file and returns a list of {@link Transaction} objects associated with the given
     * shared account identification.
     * </p>
     *
     * @param sharedAccountIdentification the identification of the shared account
     * @return a list of transactions associated with the shared account
     */
    @Override
    public List<Transaction> readTransactions(String sharedAccountIdentification) {
        List<Transaction> transactions = new ArrayList<>();

        try (TransactionIterator iterator = new TransactionIterator(transactionCsvPath)) {
            while (iterator.hasNext()) {
                Transaction transaction = iterator.next();
                String[] ids = transaction.getIdentification().split(";");
                if (ids[0].equals(sharedAccountIdentification)) {
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Sort the transactions by date
        Collections.sort(transactions, Comparator.comparing(Transaction::getDate));
        return transactions;
    }

}

