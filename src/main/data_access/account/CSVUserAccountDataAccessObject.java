package data_access.account;

import data_access.authentication.UserSignupDataAccessInterface;

import data_access.iterator.TransactionIterator;
import data_access.iterator.UserAccountIterator;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import entity.transaction.periodic.PeriodicTransaction;
//import org.w3c.dom.css.CSSStyleSheet;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;
import java.util.Map;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static java.lang.String.valueOf;

/**
 * CSVUserAccountDataAccessObject provides methods to store and retrieve user account and transaction information from CSV files.
 * <p>
 * This class implements UserAccountDataAccessInterface and UserSignupDataAccessInterface for
 * CSV-based implementations of DAOs. It is responsible for saving new user accounts and their transactions
 * into userAccounts.csv and userAccountTransactions.csv, respectively. It can also read the stored CSV files
 * to verify login information and perform various CRUD operations on user accounts and transactions.
 * </p>
 * <p>
 * It handles initialization of CSV files, ensuring headers are correctly set, and provides methods for
 * checking the existence of users, saving and updating user accounts, saving transactions, and retrieving
 * user accounts by their unique identifiers.
 * </p>
 *
 * <p>The class is authored by Jessica and Eric.</p>
 * @author Jessica
 * @author Eric
 *
 * @see UserAccount
 * @see Transaction
 * @see OneTimeTransactionOutputData
 * @see PeriodicTransactionOutputData
 *
 */
public class CSVUserAccountDataAccessObject implements UserAccountDataAccessInterface, UserSignupDataAccessInterface {
    private Map<String, UserAccount> userAccounts;
    protected static final String USER_CSV_FILE_PATH = "src/main/data/accounts/userAccounts.csv";
    protected static final String TRANSACTION_CSV_FILE_PATH = "src/main/data/transaction/userAccountTransactions.csv";
    private static final String CSV_HEADER = "id,username,password,totalIncome,totalOutflow,totalBalance,lastLoginDate";
    private static final String TRANSACTION_HEADER = "id,amount,date,description,category,start date, period, end date";

    protected final Path userCsvPath;
    protected final Path transactionCsvPath;

    /**
     * Constructs a CSVUserAccountDataAccessObject object and initializes the CSV file paths.
     * The constructor dynamically determines the base directory and initializes the CSV files
     * with correct headers if they do not exist.
     */
    public CSVUserAccountDataAccessObject() {
        // Determine the base directory dynamically
        String baseDir = System.getProperty("user.dir");
        this.userCsvPath = Paths.get(baseDir, USER_CSV_FILE_PATH);
        this.transactionCsvPath = Paths.get(baseDir, TRANSACTION_CSV_FILE_PATH);

        // define the header for csvfile
        try {
            initializeCsvFile(userCsvPath);
            initializeTransactionFile(transactionCsvPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize CSV file: " + e.getMessage());
        }
    }

    /**
     * Initializes the user accounts CSV file with the correct header.
     * If the file does not exist, it creates the file and writes the header.
     * If the file exists, it ensures the header is correct.
     *
     * @param csvPath the path to the CSV file to be initialized
     * @throws IOException if an I/O error occurs
     */
    private void initializeCsvFile(Path csvPath) throws IOException {
        // Get the parent directory of the CSV file path
        Path parentDir = csvPath.getParent();

        // If the parent directory does not exist, create it
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        // If the CSV file does not exist, create it and write the header
        if (!Files.exists(csvPath)) {
            Files.createFile(csvPath);
            try (BufferedWriter bout = Files.newBufferedWriter(csvPath, StandardOpenOption.APPEND)) {
                bout.write(CSV_HEADER);
                bout.newLine();
            }
        } else {
            // If the CSV file exists, check if the header is correct
            try (BufferedReader bin = Files.newBufferedReader(csvPath)) {
                String firstLine = bin.readLine();
                List<String> lines = new ArrayList<>();

                // If the first line is null or incorrect, rewrite the file with the correct header
                if (firstLine == null || !firstLine.equals(CSV_HEADER)) {

                    // If the first line is not the header, add it to the lines to be written back
                    if (firstLine != null && !firstLine.isEmpty()) {
                        lines.add(firstLine);
                    }

                    // Read the rest of the file
                    String line;
                    while ((line = bin.readLine()) != null) {
                        lines.add(line);
                    }

                    // Rewrite the file with the correct header and existing data
                    List<String> allLines = new ArrayList<>();
                    allLines.add(CSV_HEADER);
                    allLines.addAll(lines);

                    Files.write(csvPath, allLines, StandardOpenOption.TRUNCATE_EXISTING);
                }
            }
        }
    }

    /**
     * Initializes the transactions CSV file with the correct header.
     * If the file does not exist, it creates the file and writes the header.
     * If the file exists, it ensures the header is correct.
     *
     * @param transactionPath the path to the transactions CSV file to be initialized
     * @throws IOException if an I/O error occurs
     */
    private void initializeTransactionFile(Path transactionPath) throws IOException {
        // Get the parent directory of the CSV file path
        Path parentDir = transactionPath.getParent();

        // If the parent directory does not exist, create it
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        // If the CSV file does not exist, create it and write the header
        if (!Files.exists(transactionPath)) {
            Files.createFile(transactionPath);
            try (BufferedWriter bout = Files.newBufferedWriter(transactionPath, StandardOpenOption.APPEND)) {
                bout.write(TRANSACTION_HEADER);
                bout.newLine();
            }
        } else {
            // If the CSV file exists, check if the header is correct
            try (BufferedReader bin = Files.newBufferedReader(transactionPath)) {
                String firstLine = bin.readLine();
                List<String> lines = new ArrayList<>();

                // If the first line is null or incorrect, rewrite the file with the correct header
                if (firstLine == null || !firstLine.equals(TRANSACTION_HEADER)) {

                    // If the first line is not the header, add it to the lines to be written back
                    if (firstLine != null && !firstLine.isEmpty()) {
                        lines.add(firstLine);
                    }

                    // Read the rest of the file
                    String line;
                    while ((line = bin.readLine()) != null) {
                        lines.add(line);
                    }

                    // Rewrite the file with the correct header and existing data
                    List<String> allLines = new ArrayList<>();
                    allLines.add(TRANSACTION_HEADER);
                    allLines.addAll(lines);

                    Files.write(transactionPath, allLines, StandardOpenOption.TRUNCATE_EXISTING);
                }
            }
        }
    }

    /**
     * Checks whether a user with the specified identification exists.
     *
     * @param identification the user's unique identification
     * @return true if the user exists, false otherwise
     */
    @Override
    public boolean existById(String identification) {
        try (UserAccountIterator iterator = new UserAccountIterator(userCsvPath)) {
            while (iterator.hasNext()) {
                UserAccount userAccount = iterator.next();
                if (userAccount.getIdentification().equals(identification)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Saves a new user account to the user accounts CSV file.
     * If the user does not already exist, their information is appended to the file.
     *
     * @param userAccount the user account to be saved
     */
    @Override
    public void save(UserAccount userAccount) {
        if (!existById(userAccount.getIdentification())) {
            // user info
            String id = userAccount.getIdentification();
            String username = userAccount.getUsername();
            String password = userAccount.getPassword();
            float totalIncome = userAccount.getTotalIncome();
            float totalOutflow = userAccount.getTotalOutflow();
            float totalBalance = userAccount.getTotalBalance();
            LocalDate lastLoginDate = userAccount.getLastLoginDate();
            String stringLastLoginDate = valueOf(lastLoginDate);

            // create csv line with the user info
            String userInfo = String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s", id, username, password, totalIncome,
                    totalOutflow, totalBalance, stringLastLoginDate);

            // if csv not created, create it
            try {
                Path parentDir = userCsvPath.getParent();

                if (parentDir != null && !Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                if (!Files.exists(userCsvPath)) {
                    Files.createFile(userCsvPath);
                }

                // record the info
                try (BufferedWriter bout = Files.newBufferedWriter(userCsvPath, StandardOpenOption.APPEND)) {
                    bout.write(userInfo);
                    bout.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to write to file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves a transaction (either one-time or periodic) to the transactions CSV file.
     *
     * @param oneTimeOutputData the one-time transaction data to be saved
     * @param periodicOutputData the periodic transaction data to be saved
     * @param isPeriodic true if the transaction is periodic, false if it is one-time
     */
    @Override
    public void saveTransaction(OneTimeTransactionOutputData oneTimeOutputData,
                                PeriodicTransactionOutputData periodicOutputData,
                                boolean isPeriodic) {
        if (!isPeriodic) {
            // create csv line with the user info
            String userInfo = getTransactionInfo(oneTimeOutputData, null, false);

            // if csv not created, create it
            try {
                Path parentDir = transactionCsvPath.getParent();

                if (parentDir != null && !Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                if (!Files.exists(transactionCsvPath)) {
                    Files.createFile(transactionCsvPath);
                }

                // record the info
                try (BufferedWriter bout = Files.newBufferedWriter(transactionCsvPath, StandardOpenOption.APPEND)) {
                    bout.write(userInfo);
                    bout.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to write to file: " + e.getMessage());
            }
        } else{
            // create csv line with the user info
            String userInfo = this.getTransactionInfo(null, periodicOutputData, true);

            // if csv not created, create it
            try {
                Path parentDir = transactionCsvPath.getParent();

                if (parentDir != null && !Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                if (!Files.exists(transactionCsvPath)) {
                    Files.createFile(transactionCsvPath);
                }

                // record the info
                try (BufferedWriter bout = Files.newBufferedWriter(transactionCsvPath, StandardOpenOption.APPEND)) {
                    bout.write(userInfo);
                    bout.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to write to file: " + e.getMessage());
            }

        }
    }

    private String getTransactionInfo(OneTimeTransactionOutputData oneTimeOutputData,
                                      PeriodicTransactionOutputData periodicOutputData,
                                      boolean isPeriodic) {
        if (!isPeriodic) {
            String id = oneTimeOutputData.getId();
            float amount = oneTimeOutputData.getTransactionAmount();
            LocalDate date = oneTimeOutputData.getTransactionDate();
            String description = oneTimeOutputData.getTransactionDescription();
            String category = oneTimeOutputData.getTransactionCategory();
            String dateString = valueOf(date);

            return String.format("%s,%.2f,%s,%s,%s", id, amount, dateString, description, category);
        } else {
            String id = periodicOutputData.getId();
            float amount = periodicOutputData.getTransactionAmount();
            LocalDate startDate = periodicOutputData.getTransactionStartDate();
            LocalDate endDate = periodicOutputData.getTransactionEndDate();
            LocalDate date = periodicOutputData.getTransactionDate();
            String description = periodicOutputData.getTransactionDescription();
            String period = periodicOutputData.getTransactionPeriod();
            String category = periodicOutputData.getTransactionCategory();
            String startDateString = valueOf(startDate);
            String endDateString = valueOf(endDate);
            String dateString = valueOf(date);

            return String.format("%s,%.2f,%s,%s,%s,%s,%s,%s", id, amount, dateString, description, category, startDateString, period, endDateString);
        }
    }

    /**
     * Updates an existing user account in the user accounts CSV file.
     * The method reads the file, updates the relevant user's information, and writes the updated content back to the file.
     *
     * @param userAccount the user account with updated information
     */
    @Override
    public void update(UserAccount userAccount) {
        String identification = userAccount.getIdentification();
        List<String> lines = new ArrayList<>();
        String updatedLine = null;
        try (BufferedReader bin = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH))) {
            String line;
            while ((line = bin.readLine()) != null) {
//                line = bin.readLine();
                String[] values = line.split(",");

                // we only compare the id
                String id = values[0];
                if (id.equals(identification)) {
                    // user info
                    String username = userAccount.getUsername();
                    String password = userAccount.getPassword();
                    float income = userAccount.getTotalIncome();
                    float outflow = userAccount.getTotalOutflow();
                    float balance = userAccount.getTotalBalance();
                    LocalDate lastLoginDate = userAccount.getLastLoginDate();
                    String lastLoginDateString = valueOf(lastLoginDate);

                    updatedLine = String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s", id, username, password,
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
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_CSV_FILE_PATH),
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
     * Deletes a user account by its identification from the user accounts CSV file.
     * The method reads the file, excludes the user to be deleted, and writes the remaining data back to the file.
     *
     * @param identification the unique identification of the user to be deleted
     */
    @Override
    public void deleteById(String identification) {
        List<String> lines = new ArrayList<>();

        // Read all lines from the CSV file
        try (UserAccountIterator iterator = new UserAccountIterator(userCsvPath)) {
            while (iterator.hasNext()) {
                UserAccount userAccount = iterator.next();
                if (!userAccount.getIdentification().equals(identification)) {
                    lines.add(String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s",
                            userAccount.getIdentification(),
                            userAccount.getUsername(),
                            userAccount.getPassword(),
                            userAccount.getTotalIncome(),
                            userAccount.getTotalOutflow(),
                            userAccount.getTotalBalance(),
                            userAccount.getLastLoginDate() != null ? userAccount.getLastLoginDate() : ""));
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Write all lines back to the CSV file, excluding the deleted user
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_CSV_FILE_PATH), StandardOpenOption.TRUNCATE_EXISTING)) {
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
     * Retrieves a user account by its identification from the user accounts CSV file.
     *
     * @param identification the unique identification of the user to be retrieved
     * @return the user account if found, or null if not found
     */
    @Override
    public UserAccount getById(String identification) {
        try (UserAccountIterator iterator = new UserAccountIterator(userCsvPath)) {
            while (iterator.hasNext()) {
                UserAccount userAccount = iterator.next();
                if (userAccount.getIdentification().equals(identification)) {
                    return userAccount;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads all transactions for a specific user from the transactions CSV file.
     * This method is currently a placeholder and needs to be implemented.
     *
     * @param identification the unique identification of the user whose transactions are to be read
     * @return a list of transactions associated with the specified user
     */
    @Override
    public List<Transaction> readTransactions(String identification){
        // need implementation
        List<Transaction> transactions = new ArrayList<>();

        try (TransactionIterator iterator = new TransactionIterator(transactionCsvPath)) {
            while (iterator.hasNext()) {
                Transaction transaction = iterator.next();
                if (transaction.getIdentification().equalsIgnoreCase(identification)) {
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

    /**
     * Writes all transactions of all users to the transactions CSV file.
     *
     * @param users a map of user accounts and their associated transactions to be written to the file
     */
    private void writeAllTransactions(Map<String, UserAccount> users) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(TRANSACTION_CSV_FILE_PATH))) {
            for (UserAccount user : users.values()) {
                for (Transaction transaction : user.getTransactions()) {
                    bw.write(String.format("%s,%s,%f",
                            transaction.getIdentification(),
                            transaction.getDescription(),
                            transaction.getAmount()));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
