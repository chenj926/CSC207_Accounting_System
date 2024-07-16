package data_access;

import entity.UserAccount;
import entity.Transaction;

import java.util.Map;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVUserAccountDataAccessObject implements UserAccountDataAccessInterface, UserSignupDataAccessInterface{
    protected static final String USER_CSV_FILE_PATH = "data/userAccounts.csv";
    protected static final String TRANSACTION_CSV_FILE_PATH = "data/userAccountTransactions.csv";

    public CSVUserAccountDataAccessObject() {
        new File("data").mkdirs();
    }
    @Override
    public boolean existById(String identifier) {
        return getById(identifier) != null;
    }

    @Override
    public void save(UserAccount newUser) {
        Map<String, UserAccount> users = readAllUsers();
        users.put(newUser.getIdentification(), newUser);
        writeAllUsers(users);
    }

    @Override
    public void update(UserAccount userAccount) {
        Map<String, UserAccount> users = readAllUsers();
        users.put(userAccount.getIdentification(), userAccount);
        writeAllUsers(users);
    }

    @Override
    public void deleteById(String identifier) {
        Map<String, UserAccount> users = readAllUsers();
        users.remove(identifier);
        writeAllUsers(users);
    }

    public UserAccount getById(String identifier){
        Map<String, UserAccount> users = readAllUsers();
        return users.get(identifier);
    }

    private Map<String, UserAccount> readAllUsers() {
        Map<String, UserAccount> users = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    UserAccount user = new UserAccount(
                            values[1], values[2], values[0]); // username, password, identification
                    user.setTotalIncome(Float.parseFloat(values[3]));
                    user.setTotalOutflow(Float.parseFloat(values[4]));
                    user.setTotalBalance(Float.parseFloat(values[5]));
                    users.put(user.getIdentification(), user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void writeAllUsers(Map<String, UserAccount> users) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(USER_CSV_FILE_PATH))) {
            for (UserAccount user : users.values()) {
                bw.write(String.format("%s,%s,%s,%f,%f,%f",
                        user.getIdentification(), user.getUsername(), user.getPassword(),
                        user.getTotalIncome(), user.getTotalOutflow(), user.getTotalBalance()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeAllTransactions(users);
    }

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
