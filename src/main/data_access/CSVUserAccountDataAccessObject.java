package data_access;

import entity.*;

import java.util.Map;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVUserAccountDataAccessObject implements UserAccountDataAccessInterface, UserSignupDataAccessInterface{
    private Map<String, UserAccount> userAccounts;
    protected static final String USER_CSV_FILE_PATH = "src/main/data/userAccounts.csv";
    protected static final String TRANSACTION_CSV_FILE_PATH = "src/main/data/userAccountTransactions.csv";
    private static final String CSV_HEADER = "id,username,password,totalIncome,totalOutflow,totalBalance";

    private final Path userCsvPath;
    private final Path transactionCsvPath;

    public CSVUserAccountDataAccessObject() {
        // Determine the base directory dynamically
        String baseDir = System.getProperty("user.dir");
        this.userCsvPath = Paths.get(baseDir, USER_CSV_FILE_PATH);
        this.transactionCsvPath = Paths.get(baseDir, TRANSACTION_CSV_FILE_PATH);

        // define the header for csvfile
        try {
            initializeCsvFile(userCsvPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize CSV file: " + e.getMessage());
        }
    }

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

    @Override
    public boolean existById(String identification) {
        return readAllUsers(identification);
    }

    @Override
    public void save(UserAccount newUser) {
        if (!existById(newUser.getIdentification())) {
            // user info
            String id = newUser.getIdentification();
            String username = newUser.getUsername();
            String password = newUser.getPassword();
            float totalIncome = newUser.getTotalIncome();
            float totalOutflow = newUser.getTotalOutflow();
            float totalBalance = newUser.getTotalBalance();

            // create csv line with the user info
            String userInfo = String.format("%s,%s,%s,%.2f,%.2f,%.2f", id, username, password, totalIncome,
                    totalOutflow, totalBalance);

            // if csv not created, create it
            try {
                Path parentDir = userCsvPath.getParent();

                if (parentDir != null && !Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                if (!Files.exists(userCsvPath)) {
                    Files.createFile(userCsvPath);
                }

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

                    updatedLine = String.format("%s,%s,%s,%.2f,%.2f,%.2f", id, username, password,
                            income, outflow, balance);
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

    @Override
    public void deleteById(String identification) {
        List<String> lines = new ArrayList<>();

        // Read all lines from the CSV file
        try (BufferedReader bin = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH))) {
            String line;
            while ((line = bin.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                if (!id.equals(identification)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Write all lines back to the CSV file, excluding the deleted user
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_CSV_FILE_PATH), StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public UserAccount getById(String identification){
        UserAccount userAccount = null;
        try (BufferedReader bin = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH))) {
            String line;
            while ((line = bin.readLine()) != null) {
//                line = bin.readLine();
                String[] values = line.split(",");

                // we only compare the id
                String id = values[0];
                if (id.equals(identification)) {
                    // user info
                    String username = values[1];
                    String password = values[2];
                    float income = Float.parseFloat(values[3]);
                    float outflow = Float.parseFloat(values[4]);
                    float balance = Float.parseFloat(values[5]);

                    userAccount = new UserAccount(username, password, id, income, outflow, balance);
                    return userAccount;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return userAccount;
    }

    private boolean readAllUsers(String identification) {
//        this.userAccounts = new HashMap<>();
        boolean userExist = false;
        try (BufferedReader bin = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH))) {
            String line;
            while ((line = bin.readLine()) != null) {
//                line = bin.readLine();
                String[] values = line.split(",");

                // we only compare the id
                String id = values[0];
                if (id.equals(identification)) {
                    userExist = true;
                    return userExist;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return userExist;
    }

//    private void writeAllUsers(Map<String, UserAccount> users) {
//        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(USER_CSV_FILE_PATH))) {
//            for (UserAccount user : users.values()) {
//                bw.write(String.format("%s,%s,%s,%f,%f,%f",
//                        user.getIdentification(), user.getUsername(), user.getPassword(),
//                        user.getTotalIncome(), user.getTotalOutflow(), user.getTotalBalance()));
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        writeAllTransactions(users);
//    }

    private List<Transaction> readTransactions(String userIdentification){
        // need implementation
        List<Transaction> transactions = new ArrayList<>();
        return transactions;
    }

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
