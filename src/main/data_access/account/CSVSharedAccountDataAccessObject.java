package data_access.account;

import entity.account.SharedAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;
import entity.transaction.one_time.OneTimeTransaction;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import java.time.LocalDate;

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
public class CSVSharedAccountDataAccessObject extends CSVUserAccountDataAccessObject implements ShareAccountDataAccessInterface {
    private static final String SHARED_ACCOUNT_CSV_FILE_PATH = "src/main/data/sharedAccounts.csv";
    private static final String SHARED_ACCOUNT_USERS_CSV_FILE_PATH = "src/main/data/sharedAccountUsers.csv";
    private static final String SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH = "src/main/data/sharedAccountTransactions.csv";

    private final Path sharedAccountsCsvPath;
    /**
     * Constructs a new instance of {@code CSVSharedAccountDataAccessObject}.
     * Initializes the CSV-based data access object for shared accounts.
     */
    public CSVSharedAccountDataAccessObject() {
        super();
        this.sharedAccountsCsvPath = Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH);
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
        return getById(sharedAccountIdentification) != null;
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
        Map<String, SharedAccount> sharedAccounts = readAllSharedAccounts();
        sharedAccounts.put(newSharedAccount.getIdentification(), newSharedAccount);
        writeAllSharedAccounts(sharedAccounts);
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
    public void update(SharedAccount sharedAccount) {
        Map<String, SharedAccount> sharedAccounts = readAllSharedAccounts();
        sharedAccounts.put(sharedAccount.getIdentification(), sharedAccount);
        writeAllSharedAccounts(sharedAccounts);
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
        Map<String, SharedAccount> sharedAccounts = readAllSharedAccounts();
        sharedAccounts.remove(sharedAccountIdentification);
        writeAllSharedAccounts(sharedAccounts);
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
        Map<String, SharedAccount> sharedAccounts = readAllSharedAccounts();
        return sharedAccounts.get(sharedAccountIdentification);
    }

    /**
     * Reads all shared accounts from the CSV file.
     * <p>
     * This method parses the CSV file and creates {@link SharedAccount} objects for each record.
     * </p>
     *
     * @return a map of shared accounts with their identification as keys
     */
    private Map<String, SharedAccount> readAllSharedAccounts() {
        Map<String, SharedAccount> sharedAccounts = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    String sharedAccountIdentification = values[0];
                    String sharedAccountPassword = values[1];
                    SharedAccount sharedAccount = new SharedAccount(sharedAccountIdentification, sharedAccountPassword);

                    sharedAccount.setUsername(values[2]);
                    sharedAccount.setPassword(values[3]);
                    sharedAccount.setTotalIncome(Float.parseFloat(values[4]));
                    sharedAccount.setTotalOutflow(Float.parseFloat(values[5]));
                    sharedAccount.setTotalBalance(Float.parseFloat(values[6]));

                    // Read and set shared user identifications
                    sharedAccount.setSharedUserIdentifications(readSharedAccountUsers(sharedAccount.getIdentification()));

                    // Read and set shared account transactions
                    sharedAccount.setTransactions(readSharedAccountTransactions(sharedAccount.getIdentification()));

                    sharedAccounts.put(sharedAccount.getIdentification(), sharedAccount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sharedAccounts;
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
    private Set<String> readSharedAccountUsers(String sharedAccountIdentification) {
        Set<String> userIds = new HashSet<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_USERS_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2 && values[0].equals(sharedAccountIdentification)) {
                    userIds.add(values[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userIds;
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
    private List<Transaction> readSharedAccountTransactions(String sharedAccountIdentification) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(sharedAccountIdentification)) {
                    Transaction transaction = createTransaction(values);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    /**
     * Writes all shared accounts to the CSV file.
     * <p>
     * This method serializes the shared accounts and their associated users and transactions to CSV files.
     * </p>
     *
     * @param sharedAccounts a map of shared accounts with their identification as keys
     */
    private void writeAllSharedAccounts(Map<String, SharedAccount> sharedAccounts) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
            for (SharedAccount sharedAccount : sharedAccounts.values()) {
                bw.write(String.format("%s,%s,%s,%f,%f,%f",
                        sharedAccount.getIdentification(),
                        sharedAccount.getUsername(),
                        sharedAccount.getPassword(),
                        sharedAccount.getTotalIncome(),
                        sharedAccount.getTotalOutflow(),
                        sharedAccount.getTotalBalance()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeAllSharedAccountUsers(sharedAccounts);
        writeAllSharedAccountTransactions(sharedAccounts);
    }

    /**
     * Writes the users associated with shared accounts to the CSV file.
     * <p>
     * This method serializes the user IDs associated with each shared account to the CSV file.
     * </p>
     *
     * @param sharedAccounts a map of shared accounts with their identification as keys
     */
    private void writeAllSharedAccountUsers(Map<String, SharedAccount> sharedAccounts) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_USERS_CSV_FILE_PATH))) {
            for (SharedAccount sharedAccount : sharedAccounts.values()) {
                for (String userId : sharedAccount.getSharedUserIdentifications()) {
                    bw.write(String.format("%s,%s",
                            sharedAccount.getIdentification(), userId));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the transactions associated with shared accounts to the CSV file.
     * <p>
     * This method serializes the transactions associated with each shared account to the CSV file.
     * </p>
     *
     * @param sharedAccounts a map of shared accounts with their identification as keys
     */
    private void writeAllSharedAccountTransactions(Map<String, SharedAccount> sharedAccounts) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH))) {
            for (SharedAccount sharedAccount : sharedAccounts.values()) {
                for (Transaction transaction : sharedAccount.getTransactions()) {
                    if (transaction instanceof PeriodicTransaction) {
                        PeriodicTransaction pt = (PeriodicTransaction) transaction;
                        bw.write(String.format("%s,periodic,%s,%f,%s,%s,%s,%d,%b",
                                sharedAccount.getIdentification(), pt.getIdentification(), pt.getAmount(), pt.getStartDate(),
                                pt.getDescription(), pt.getEndDate(), pt.getPeriod(), pt.isInflow()));
                    } else if (transaction instanceof OneTimeTransaction) {
                        OneTimeTransaction ot = (OneTimeTransaction) transaction;
                        bw.write(String.format("%s,onetime,%s,%f,%s,%s,%s,,,%b",
                                sharedAccount.getIdentification(), ot.getIdentification(), ot.getAmount(), ot.getDate(),
                                ot.getDescription(), ot.getTransactionCategory(), ot.isInflow()));
                    }
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a {@link Transaction} object from a CSV record.
     * <p>
     * This method parses the CSV record and constructs the appropriate type of transaction based on the provided
     * type and other details.
     * </p>
     *
     * @param values an array of values from the CSV record
     * @return a {@link Transaction} object created from the CSV record, or {@code null} if the type is unknown
     */
    private Transaction createTransaction(String[] values) {
        String type = values[1];
        String identification = values[2];
        float amount = Float.parseFloat(values[3]);
        LocalDate date = LocalDate.parse(values[4]);
        String description = values[5];
        boolean isInflow = Boolean.parseBoolean(values[10]);

        if (type.equals("periodic")) {
            LocalDate startDate = LocalDate.parse(values[4]);
            LocalDate endDate = LocalDate.parse(values[8]);
            int period = Integer.parseInt(values[9]);

            if(isInflow){
                return new PeriodicInflow(identification, amount, startDate, description, endDate, period, "Auto");
            }else{
                return new PeriodicOutflow(identification, amount, startDate, description, endDate, period, "Auto");
            }

        } else if (type.equals("onetime")) {
            String category = values[6];
            if(isInflow){
                return new OneTimeInflow(identification, amount, date, description, category);
            }else{
                return new OneTimeOutflow(identification, amount, date, description, category);
            }
        }
        return null;
    }
}

