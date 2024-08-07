package data_access.account;

import data_access.authentication.SharedAccountLoginDataAccessInterface;
import entity.account.SharedAccount;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;
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
public class CSVSharedAccountDataAccessObject extends CSVAccountDataAccessObject<SharedAccount> implements ShareAccountDataAccessInterface {
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
            String userInfo = String.format("%s,%s,%s,%s,%.2f,%.2f,%.2f,%s", id, stringUserIds, username, password,
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

//    /**
//     * Reads all shared accounts from the CSV file.
//     * <p>
//     * This method parses the CSV file and creates {@link SharedAccount} objects for each record.
//     * </p>
//     *
//     * @return a map of shared accounts with their identification as keys
//     */
//    private Map<String, SharedAccount> readAllSharedAccounts() {
//        Map<String, SharedAccount> sharedAccounts = new HashMap<>();
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                if (values.length == 6) {
//                    String sharedAccountIdentification = values[0];
//                    String sharedAccountPassword = values[1];
//                    SharedAccount sharedAccount = new SharedAccount(sharedAccountIdentification, sharedAccountPassword);
//
//                    sharedAccount.setUsername(values[2]);
//                    sharedAccount.setPassword(values[3]);
//                    sharedAccount.setTotalIncome(Float.parseFloat(values[4]));
//                    sharedAccount.setTotalOutflow(Float.parseFloat(values[5]));
//                    sharedAccount.setTotalBalance(Float.parseFloat(values[6]));
//
//                    // Read and set shared user identifications
//                    sharedAccount.setSharedUserIdentifications(readSharedAccountUsers(sharedAccount.getIdentification()));
//
//                    // Read and set shared account transactions
//                    sharedAccount.setTransactions(readSharedAccountTransactions(sharedAccount.getIdentification()));
//
//                    sharedAccounts.put(sharedAccount.getIdentification(), sharedAccount);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sharedAccounts;
//    }


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

//    /**
//     * Writes all shared accounts to the CSV file.
//     * <p>
//     * This method serializes the shared accounts and their associated users and transactions to CSV files.
//     * </p>
//     *
//     * @param sharedAccounts a map of shared accounts with their identification as keys
//     */
//    private void writeAllSharedAccounts(Map<String, SharedAccount> sharedAccounts) {
//        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
//            for (SharedAccount sharedAccount : sharedAccounts.values()) {
//                bw.write(String.format("%s,%s,%s,%f,%f,%f",
//                        sharedAccount.getIdentification(),
//                        sharedAccount.getUsername(),
//                        sharedAccount.getPassword(),
//                        sharedAccount.getTotalIncome(),
//                        sharedAccount.getTotalOutflow(),
//                        sharedAccount.getTotalBalance()));
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        writeAllSharedAccountUsers(sharedAccounts);
//        writeAllSharedAccountTransactions(sharedAccounts);
//    }
//
//    /**
//     * Writes the users associated with shared accounts to the CSV file.
//     * <p>
//     * This method serializes the user IDs associated with each shared account to the CSV file.
//     * </p>
//     *
//     * @param sharedAccounts a map of shared accounts with their identification as keys
//     */
//    private void writeAllSharedAccountUsers(Map<String, SharedAccount> sharedAccounts) {
//        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_USERS_CSV_FILE_PATH))) {
//            for (SharedAccount sharedAccount : sharedAccounts.values()) {
//                for (String userId : sharedAccount.getSharedUserIdentifications()) {
//                    bw.write(String.format("%s,%s",
//                            sharedAccount.getIdentification(), userId));
//                    bw.newLine();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Writes the transactions associated with shared accounts to the CSV file.
//     * <p>
//     * This method serializes the transactions associated with each shared account to the CSV file.
//     * </p>
//     *
//     * @param sharedAccounts a map of shared accounts with their identification as keys
//     */
//    private void writeAllSharedAccountTransactions(Map<String, SharedAccount> sharedAccounts) {
//        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH))) {
//            for (SharedAccount sharedAccount : sharedAccounts.values()) {
//                for (Transaction transaction : sharedAccount.getTransactions()) {
//                    if (transaction instanceof PeriodicTransaction) {
//                        PeriodicTransaction pt = (PeriodicTransaction) transaction;
//                        bw.write(String.format("%s,periodic,%s,%f,%s,%s,%s,%d,%b",
//                                sharedAccount.getIdentification(), pt.getIdentification(), pt.getAmount(), pt.getStartDate(),
//                                pt.getDescription(), pt.getEndDate(), pt.getPeriod(), pt.isInflow()));
//                    } else if (transaction instanceof OneTimeTransaction) {
//                        OneTimeTransaction ot = (OneTimeTransaction) transaction;
//                        bw.write(String.format("%s,onetime,%s,%f,%s,%s,%s,,,%b",
//                                sharedAccount.getIdentification(), ot.getIdentification(), ot.getAmount(), ot.getDate(),
//                                ot.getDescription(), ot.getTransactionCategory(), ot.isInflow()));
//                    }
//                    bw.newLine();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * Creates a {@link Transaction} object from a CSV record.
//     * <p>
//     * This method parses the CSV record and constructs the appropriate type of transaction based on the provided
//     * type and other details.
//     * </p>
//     *
//     * @param values an array of values from the CSV record
//     * @return a {@link Transaction} object created from the CSV record, or {@code null} if the type is unknown
//     */
//    private Transaction createTransaction(String[] values) {
//        String type = values[1];
//        String identification = values[2];
//        float amount = Float.parseFloat(values[3]);
//        LocalDate date = LocalDate.parse(values[4]);
//        String description = values[5];
//        boolean isInflow = Boolean.parseBoolean(values[10]);
//
//        if (type.equals("periodic")) {
//            LocalDate startDate = LocalDate.parse(values[4]);
//            LocalDate endDate = LocalDate.parse(values[8]);
//            String period = values[9];
//
//            if(isInflow){
//                return new PeriodicInflow(identification, amount, startDate, description, endDate, period, "Auto");
//            }else{
//                return new PeriodicOutflow(identification, amount, startDate, description, endDate, period, "Auto");
//            }
//
//        } else if (type.equals("onetime")) {
//            String category = values[6];
//            if(isInflow){
//                return new OneTimeInflow(identification, amount, date, description, category);
//            }else{
//                return new OneTimeOutflow(identification, amount, date, description, category);
//            }
//        }
//        return null;
//    }


//    @Override
//    public void saveSharedTransaction(SharedAccountOneTimeTransactionOutputData oneTimeOutputData,
//                                SharedAccountPeriodicTransactionOutputData periodicOutputData,
//                                boolean isPeriodic) {
//        String transactionInfo;
//
//        if (!isPeriodic) {
//            // Get the CSV line with one-time transaction information
//            transactionInfo = getTransactionInfo(oneTimeOutputData, null, false);
//        } else {
//            // Get the CSV line with periodic transaction information
//            transactionInfo = getTransactionInfo(null, periodicOutputData, true);
//        }
//
//        // Save the transaction information to the CSV file
//        try {
//            Path parentDir = sharedAccountsTransactionsCsvPath.getParent();
//
//            if (parentDir != null && !Files.exists(parentDir)) {
//                Files.createDirectories(parentDir);
//            }
//
//            if (!Files.exists(sharedAccountsTransactionsCsvPath)) {
//                Files.createFile(sharedAccountsTransactionsCsvPath);
//            }
//
//            // Write the transaction info to the CSV
//            try (BufferedWriter bout = Files.newBufferedWriter(sharedAccountsTransactionsCsvPath, StandardOpenOption.APPEND)) {
//                bout.write(transactionInfo);
//                bout.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("Failed to write to file: " + e.getMessage());
//        }
//    }

//    /**
//     * Generates a CSV string representation of a transaction.
//     *
//     * @param oneTimeOutputData   the one-time transaction data
//     * @param periodicOutputData  the periodic transaction data
//     * @param isPeriodic          true if the transaction is periodic, false if it is one-time
//     * @return the CSV string representation of the transaction
//     */
//    private String getTransactionInfo(SharedAccountOneTimeTransactionOutputData oneTimeOutputData,
//                                      SharedAccountPeriodicTransactionOutputData periodicOutputData,
//                                      boolean isPeriodic) {
//        if (!isPeriodic && oneTimeOutputData != null) {
//            // One-time transaction fields
//            return String.format("%s,%f,%s,%s,%s,%s",
//                    oneTimeOutputData.getId(),
//                    oneTimeOutputData.getTransactionAmount(),
//                    oneTimeOutputData.getTransactionDate(),
//                    oneTimeOutputData.getTransactionDescription(),
//                    oneTimeOutputData.getTransactionCategory(),
//                    oneTimeOutputData.getResponsibleUserIds());
//        } else if (isPeriodic && periodicOutputData != null) {
//            // Periodic transaction fields
//            return String.format("%s,%f,%s,%s,%s,%s,%s",
//                    periodicOutputData.getId(),
//                    periodicOutputData.getTransactionAmount(),
//                    periodicOutputData.getTransactionStartDate(),
//                    periodicOutputData.getTransactionEndDate(),
//                    periodicOutputData.getTransactionPeriod(),
//                    periodicOutputData.getTransactionDescription(),
//                    periodicOutputData.getResponsibleUserIds());
//        }
//        return "";
//    }
}

