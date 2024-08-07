package data_access.account;

import entity.account.Account;
import entity.account.SharedAccount;
import entity.account.UserAccount;
import entity.transaction.Transaction;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.String.valueOf;

public abstract class CSVAccountDataAccessObject<T extends Account> {
    protected final Path accountCsvPath;
    protected final Path transactionCsvPath;
    protected final String csvHeader;
    protected final String transactionHeader;

    public CSVAccountDataAccessObject(String accountCsvFilePath, String transactionCsvFilePath, String csvHeader, String transactionHeader) {
        String baseDir = System.getProperty("user.dir");
        this.accountCsvPath = Paths.get(baseDir, accountCsvFilePath);
        this.transactionCsvPath = Paths.get(baseDir, transactionCsvFilePath);
        this.csvHeader = csvHeader;
        this.transactionHeader = transactionHeader;

        try {
            initializeCsvFile(accountCsvPath, csvHeader);
            initializeTransactionFile(transactionCsvPath, transactionHeader);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize CSV file: " + e.getMessage());
        }
    }

    private void initializeCsvFile(Path csvPath, String header) throws IOException {
        Path parentDir = csvPath.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        if (!Files.exists(csvPath)) {
            Files.createFile(csvPath);
            try (BufferedWriter bout = Files.newBufferedWriter(csvPath, StandardOpenOption.APPEND)) {
                bout.write(header);
                bout.newLine();
            }
        } else {
            try (BufferedReader bin = Files.newBufferedReader(csvPath)) {
                String firstLine = bin.readLine();
                if (firstLine == null || !firstLine.equals(header)) {
                    List<String> lines = new ArrayList<>();
                    if (firstLine != null && !firstLine.isEmpty()) {
                        lines.add(firstLine);
                    }
                    String line;
                    while ((line = bin.readLine()) != null) {
                        lines.add(line);
                    }
                    lines.add(0, header);
                    Files.write(csvPath, lines, StandardOpenOption.TRUNCATE_EXISTING);
                }
            }
        }
    }

    private void initializeTransactionFile(Path transactionPath, String header) throws IOException {
        Path parentDir = transactionPath.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        if (!Files.exists(transactionPath)) {
            Files.createFile(transactionPath);
            try (BufferedWriter bout = Files.newBufferedWriter(transactionPath, StandardOpenOption.APPEND)) {
                bout.write(header);
                bout.newLine();
            }
        } else {
            try (BufferedReader bin = Files.newBufferedReader(transactionPath)) {
                String firstLine = bin.readLine();
                if (firstLine == null || !firstLine.equals(header)) {
                    List<String> lines = new ArrayList<>();
                    if (firstLine != null && !firstLine.isEmpty()) {
                        lines.add(firstLine);
                    }
                    String line;
                    while ((line = bin.readLine()) != null) {
                        lines.add(line);
                    }
                    lines.add(0, header);
                    Files.write(transactionPath, lines, StandardOpenOption.TRUNCATE_EXISTING);
                }
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
    public void saveTransaction(OneTimeTransactionOutputData oneTimeOutputData,
                                PeriodicTransactionOutputData periodicOutputData,
                                boolean isPeriodic) {
        if (!isPeriodic) {
            // create csv line with the user info
            String userInfo = getTransactionInfo(oneTimeOutputData, null, false);
            // if csv not created, create it
            confirmCsvExistence(transactionCsvPath, userInfo);
        } else{
            // create csv line with the user info
            String userInfo = getTransactionInfo(null, periodicOutputData, true);
            // if csv not created, create it
            confirmCsvExistence(transactionCsvPath, userInfo);

        }
    }

    protected void confirmCsvExistence(Path transactionCsvPath, String userInfo) {
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

    protected static String getPeriodicTransactionInfo(PeriodicTransactionOutputData periodicOutputData) {
        String id = periodicOutputData.getId();
        float amount = periodicOutputData.getTransactionAmount();
        String startDate = valueOf(periodicOutputData.getTransactionStartDate());
        String endDate = valueOf(periodicOutputData.getTransactionEndDate());
        String date = valueOf(periodicOutputData.getTransactionDate());
        String description = periodicOutputData.getTransactionDescription();
        String period = periodicOutputData.getTransactionPeriod();
        String category = periodicOutputData.getTransactionCategory();
        return String.format("%s,%.2f,%s,%s,%s,%s,%s,%s", id, amount, date, description, category, startDate, period, endDate);
    }

    protected static String getOneTimeTransactionInfo(OneTimeTransactionOutputData oneTimeOutputData) {
        String id = oneTimeOutputData.getId();
        float amount = oneTimeOutputData.getTransactionAmount();
        String date = valueOf(oneTimeOutputData.getTransactionDate());
        String description = oneTimeOutputData.getTransactionDescription();
        String category = oneTimeOutputData.getTransactionCategory();
        return String.format("%s,%.2f,%s,%s,%s", id, amount, date, description, category);
    }

    protected String getTransactionInfo(OneTimeTransactionOutputData oneTimeOutputData,
                                        PeriodicTransactionOutputData periodicOutputData,
                                        boolean isPeriodic) {
        if (!isPeriodic) {
            return getOneTimeTransactionInfo(oneTimeOutputData);
        } else {
            return getPeriodicTransactionInfo(periodicOutputData);
        }
    }


    public abstract boolean existById(String identification);

    public abstract void save(T account);

    public void update(Account account) {
        String identification = account.getIdentification();
        List<String> lines = new ArrayList<>();
        String updatedLine = null;
        try (BufferedReader bin = Files.newBufferedReader(accountCsvPath)) {
            String line;
            while ((line = bin.readLine()) != null) {
//                line = bin.readLine();
                String[] values = line.split(",");

                // we only compare the id
                String id = values[0];
                if (id.equals(identification)) {
                    // user info

                    String password = account.getPassword();
                    float income = account.getTotalIncome();
                    float outflow = account.getTotalOutflow();
                    float balance = account.getTotalBalance();
                    LocalDate lastLoginDate = account.getLastLoginDate();
                    String lastLoginDateString = valueOf(lastLoginDate);

                    if (account instanceof SharedAccount) {
                        String userIds = ((SharedAccount) account).getSharedUserIdentifications().toString();
                        updatedLine = String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s", id, userIds, password,
                                income, outflow, balance, lastLoginDateString);

                    }else if (account instanceof UserAccount){
                        String username = ((UserAccount)account).getUsername();
                        updatedLine = String.format("%s,%s,%s,%.2f,%.2f,%.2f,%s", id, username, password,
                                income, outflow, balance, lastLoginDateString);
                    }

                    lines.add(updatedLine);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public abstract void deleteById(String identification);

    public abstract T getById(String identification);

    protected abstract boolean readAllUsers(String identification);

    public abstract List<Transaction> readTransactions(String identification);

    protected abstract Transaction getTransactions(String[] values);
}

