package data_access.account;

import entity.account.Account;
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
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public abstract class CSVAccountDataAccessObject<T extends Account> {
    protected final Path accountCsvPath;
    protected final Path transactionCsvPath;
    private final String csvHeader;
    private final String transactionHeader;

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

    public void saveTransaction(OneTimeTransactionOutputData oneTimeOutputData, PeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {
        String transactionInfo = getTransactionInfo(oneTimeOutputData, periodicOutputData, isPeriodic);
        try {
            Path parentDir = this.transactionCsvPath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            if (!Files.exists(this.transactionCsvPath)) {
                Files.createFile(this.transactionCsvPath);
            }
            try (BufferedWriter bout = Files.newBufferedWriter(this.transactionCsvPath, StandardOpenOption.APPEND)) {
                bout.write(transactionInfo);
                bout.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    protected abstract String getTransactionInfo(OneTimeTransactionOutputData oneTimeOutputData, PeriodicTransactionOutputData periodicOutputData, boolean isPeriodic);


    public abstract boolean existById(String identification);

    public abstract void save(T account);

    public abstract void update(T account);

    public abstract void deleteById(String identification);

    public abstract T getById(String identification);

    protected abstract boolean readAllUsers(String identification);

    public abstract List<Transaction> readTransactions(String identification);

    protected abstract Transaction getTransactions(String[] values);
}

