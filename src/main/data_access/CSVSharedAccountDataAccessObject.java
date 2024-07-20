package data_access;

import entity.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import java.time.LocalDate;

public class CSVSharedAccountDataAccessObject extends CSVUserAccountDataAccessObject implements ShareAccountDataAccessInterface {
    private static final String SHARED_ACCOUNT_CSV_FILE_PATH = "src/main/data/sharedAccounts.csv";
    private static final String SHARED_ACCOUNT_USERS_CSV_FILE_PATH = "src/main/data/sharedAccountUsers.csv";
    private static final String SHARED_ACCOUNT_TRANSACTIONS_CSV_FILE_PATH = "src/main/data/sharedAccountTransactions.csv";

    public CSVSharedAccountDataAccessObject() {
        super();
    }

    @Override
    public boolean existById(String sharedAccountIdentification) {
        return getById(sharedAccountIdentification) != null;
    }

    @Override
    public void save(SharedAccount newSharedAccount) {
        Map<String, SharedAccount> sharedAccounts = readAllSharedAccounts();
        sharedAccounts.put(newSharedAccount.getIdentification(), newSharedAccount);
        writeAllSharedAccounts(sharedAccounts);
    }


    public void update(SharedAccount sharedAccount) {
        Map<String, SharedAccount> sharedAccounts = readAllSharedAccounts();
        sharedAccounts.put(sharedAccount.getIdentification(), sharedAccount);
        writeAllSharedAccounts(sharedAccounts);
    }

    @Override
    public void deleteById(String sharedAccountIdentification) {
        Map<String, SharedAccount> sharedAccounts = readAllSharedAccounts();
        sharedAccounts.remove(sharedAccountIdentification);
        writeAllSharedAccounts(sharedAccounts);
    }

    @Override
    public SharedAccount getById(String sharedAccountIdentification) {
        Map<String, SharedAccount> sharedAccounts = readAllSharedAccounts();
        return sharedAccounts.get(sharedAccountIdentification);
    }

    // read
    private Map<String, SharedAccount> readAllSharedAccounts() {
        Map<String, SharedAccount> sharedAccounts = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(SHARED_ACCOUNT_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    SharedAccount sharedAccount = new SharedAccount(values[0]);
                    sharedAccount.setUsername(values[1]);
                    sharedAccount.setPassword(values[2]);

                    sharedAccount.setTotalIncome(Float.parseFloat(values[3]));
                    sharedAccount.setTotalOutflow(Float.parseFloat(values[4]));
                    sharedAccount.setTotalBalance(Float.parseFloat(values[5]));

                    sharedAccount.setSharedUserIdentifications(readSharedAccountUsers(sharedAccount.getIdentification()));
                    sharedAccount.setTransactions(readSharedAccountTransactions(sharedAccount.getIdentification()));
                    sharedAccounts.put(sharedAccount.getIdentification(), sharedAccount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sharedAccounts;
    }

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

    // write
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
                                ot.getDescription(), ot.getCategory(), ot.isInflow()));
                    }
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                return new PeriodicInflow(identification, amount, startDate, description, endDate, period);
            }else{
                return new PeriodicOutflow(identification, amount, startDate, description, endDate, period);
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

