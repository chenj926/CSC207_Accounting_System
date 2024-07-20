package data_access;

import entity.*;

import java.util.Map;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVUserAccountDataAccessObject implements UserAccountDataAccessInterface, UserSignupDataAccessInterface{
    protected final File userCsvFile;
    private Map<String, UserAccount> userAccounts;
    protected static final String USER_CSV_FILE_PATH = "src/main/data/userAccounts.csv";
    protected static final String TRANSACTION_CSV_FILE_PATH = "src/main/data/userAccountTransactions.csv";

    public CSVUserAccountDataAccessObject() throws IOException {
        this.userCsvFile = new File(USER_CSV_FILE_PATH);
//        this.accountFactory = accountFactory;
        // new File("data").mkdirs(); // 有问题
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

            try (BufferedWriter bout = Files.newBufferedWriter(Paths.get(USER_CSV_FILE_PATH))) {
                bout.write(userInfo);
                bout.newLine();
            } catch (IOException e) {
                System.err.println(e.getMessage());
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

//    public UserAccount getById(String identifier){
//        Map<String, UserAccount> users = readAllUsers();
//        return users.get(identifier);
//    }

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
