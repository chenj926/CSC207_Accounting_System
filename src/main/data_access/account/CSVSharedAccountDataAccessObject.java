package data_access.account;

import data_access.authentication.SharedAccountLoginDataAccessInterface;
import entity.account.SharedAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputData;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static java.util.Arrays.stream;

/**
 * A CSV-based implementation of data access for shared accounts.
 * <p>
 * This class extends {@link CSVUserAccountDataAccessObject} and implements {@link ShareAccountDataAccessInterface}.
 * It provides methods to manage shared accounts, including saving, updating, deleting, and retrieving shared account
 * information from CSV files.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class CSVSharedAccountDataAccessObject extends CSVAccountDataAccessObject<SharedAccount, SharedAccountOneTimeTransactionOutputData, SharedAccountPeriodicTransactionOutputData> implements ShareAccountDataAccessInterface {
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
        return readAllUsers(sharedAccountIdentification);
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
            // user info
            String id = newSharedAccount.getIdentification();
            Set<String> userIds = newSharedAccount.getSharedUserIdentifications();
            String stringUserIds = String.join(";", userIds);
            String username = newSharedAccount.getUsername();
            String password = newSharedAccount.getPassword();
            float totalIncome = newSharedAccount.getTotalIncome();
            float totalOutflow = newSharedAccount.getTotalOutflow();
            float totalBalance = newSharedAccount.getTotalBalance();
            LocalDate lastLoginDate = newSharedAccount.getLastLoginDate();
            String stringLastLoginDate = valueOf(lastLoginDate);

            // create csv line with the user info
            String userInfo = String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s", id, stringUserIds, password,
                    totalIncome, totalOutflow, totalBalance, stringLastLoginDate);

            // if csv not created, create it
            confirmCsvExistence(this.accountCsvPath, userInfo);
        }
    }

    /**
     * Updates an existing shared account in the CSV file.
     * <p>
     * This method reads the existing shared accounts from the CSV file, updates the shared account,
     * and writes all shared accounts back to the file.
     * </p>
     *
     * @param sharedAccount the {@link SharedAccount} to be updated
     */
    @Override
    public void update(SharedAccount sharedAccount) {
        String identification = sharedAccount.getIdentification();
        List<String> lines = new ArrayList<>();
        String updatedLine = null;
        try (BufferedReader bin = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
            String line;
            while ((line = bin.readLine()) != null) {
//                line = bin.readLine();
                String[] values = line.split(",");

                // we only compare the id
                String id = values[0];
                if (id.equals(identification)) {
                    // user info
                    String userIds = sharedAccount.getSharedUserIdentifications().toString();
                    String password = sharedAccount.getPassword();
                    float income = sharedAccount.getTotalIncome();
                    float outflow = sharedAccount.getTotalOutflow();
                    float balance = sharedAccount.getTotalBalance();
                    LocalDate lastLoginDate = sharedAccount.getLastLoginDate();
                    String lastLoginDateString = valueOf(lastLoginDate);

                    updatedLine = String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s", id, userIds, password,
                            income, outflow, balance, lastLoginDateString);
                    lines.add(updatedLine);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // updateLine is updated, pass in all the info back
        if (updatedLine != null) {
            // open while csv, delete every thing
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH),
                    StandardOpenOption.TRUNCATE_EXISTING)) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
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
        try (BufferedReader bin = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
            String line;
            while ((line = bin.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                if (!id.equals(sharedAccountIdentification)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Write all lines back to the CSV file, excluding the deleted user
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH), StandardOpenOption.TRUNCATE_EXISTING)) {
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
        SharedAccount sharedAccount = null;
        try (BufferedReader bin = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
            String line;
            while ((line = bin.readLine()) != null) {
                String[] values = line.split(",");

                // we only compare the id
                String id = values[0];
                if (id.equals(sharedAccountIdentification)) {
                    // user info
                    Set<String> userIds = new HashSet<>();
                    // string userIds were separated by ";" instead of ","
                    String[] stringUserIds = values[1].split(";");
                    userIds.addAll(Arrays.asList(stringUserIds));

                    String password = values[2];
                    float income = Float.parseFloat(values[3]);
                    float outflow = Float.parseFloat(values[4]);
                    float balance = Float.parseFloat(values[5]);
                    LocalDate lastLoginDate = LocalDate.parse(values[6]);

                    sharedAccount = new SharedAccount(id, userIds, password, income, outflow, balance);
                    sharedAccount.setLastLoginDate(lastLoginDate);
                    return sharedAccount;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return sharedAccount;
    }

    /**
     * Reads the users associated with a shared account from the CSV file.
     * <p>
     * This method parses the CSV file and returns a set of user IDs associated with the given shared account identification.
     * </p>
     *
     * @param sharedAccountIdentification the identification of the shared account
     * @return a set of user IDs associated with the shared account
     */
    protected boolean readAllUsers(String sharedAccountIdentification) {
        boolean userExist = false;
        try (BufferedReader bin = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
            String line;
            boolean isFirstLine = true;

            while ((line = bin.readLine()) != null) {
                // Skip the header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                // we only compare the id
                String id = values[0].trim().toLowerCase();

                if (id.equals(sharedAccountIdentification.trim().toLowerCase())) {
                    userExist = true;
                    return userExist;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return userExist;
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

        try (BufferedReader bin = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH))) {
            String line;
            boolean isFirstLine = true;

            while ((line = bin.readLine()) != null) {
                // Skip the header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                // we only compare the id
                String id = values[0].trim().toLowerCase();

                if (id.equals(sharedAccountIdentification.trim().toLowerCase())) {
                    Transaction transaction = getTransactions(values);
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

    // helper method
    protected Transaction getTransactions(String[] values) {
        Transaction transaction;

        // if it is onetime
        if (values[5].equals("")) {
            String id = values[0];
            float amount = Float.parseFloat(values[1]);
            LocalDate date = LocalDate.parse(values[2]);
            String description = values[3];
            String category = values[4];

            transaction = new OneTimeTransaction(id, amount, date, description, category);

            // if it is periodc
        } else {
            String id = values[0];
            float amount = Float.parseFloat(values[1]);
            LocalDate date = LocalDate.parse(values[2]);
            String description = values[3];
            String category = values[4];
            LocalDate startDate = LocalDate.parse(values[5]);
            String period = values[6];
            LocalDate endDate = LocalDate.parse(values[7]);

            transaction = new PeriodicTransaction(id, amount, startDate, description, endDate, period, category);
            transaction.setDate(date);
        }

        return transaction;
    }
}

