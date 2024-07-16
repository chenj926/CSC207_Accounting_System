package data_access;

import entity.UserAccount;
import entity.Transaction;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVUserAccountDataAccessObject implements UserAccountDataAccessInterface, UserSignupDataAccessInterface{
    private static final String USER_CSV_FILE_PATH = "data/userAccounts.csv";
    private static final String TRANSACTION_CSV_FILE_PATH = "data/transactions.csv";

    public CSVUserAccountDataAccessObject() {
        new File("data").mkdirs();
    }
    @Override
    public boolean existById(String identifier) {
        return getById(identifier) != null;
    }

    @Override
    public void save(UserAccount newUser) {
        List<UserAccount> users = readAllUsers();
        users.add(newUser);
        writeAllUsers(users);
    }

    @Override
    public void update(UserAccount userAccount) {
        List<UserAccount> users = readAllUsers();
        users.removeIf(user -> user.getIdentification().equals(userAccount.getIdentification()));
        users.add(userAccount);
        writeAllUsers(users);
    }

    @Override
    public void deleteById(String identifier) {
        List<UserAccount> users = readAllUsers();
        users.removeIf(user -> user.getIdentification().equals(identifier));
        writeAllUsers(users);
    }

    private List<UserAccount> readAllUsers() {
        List<UserAccount> users = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // depends on how writing works
                // assume
                if (values.length == 6) {
                    UserAccount user = new UserAccount(
                            values[1], values[2], values[0]); // username, password, identification
                    user.setTotalIncome(Float.parseFloat(values[3]));
                    user.setTotalOutflow(Float.parseFloat(values[4]));
                    user.setTotalBalance(Float.parseFloat(values[5]));
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void writeAllUsers(List<UserAccount> users) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(USER_CSV_FILE_PATH))) {
            for (UserAccount user : users) {
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

    private void writeAllTransactions(List<UserAccount> users) {
        // need implementation
    }


}
