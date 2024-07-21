package data_access;

import entity.UserAccount;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class CSVUserLoginoutDataAccessObject extends CSVUserAccountDataAccessObject implements LogoutDataAccessInterface, LoginDataAccessInterface {
    private static final String FILE_PATH = CSVUserAccountDataAccessObject.USER_CSV_FILE_PATH;
//    private Map<String, Boolean> userLogin;

    public CSVUserLoginoutDataAccessObject() {
        super();

        // 保留一下login state的file，可以显示这个用户是不是logged in， 如果是的话就一直保留logged in

//        userLogin = new HashMap<>();
//        loadFromCsv();
    }

//    private void loadFromCsv() {
//        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 2) {
//                    userLogin.put(parts[0], Boolean.parseBoolean(parts[1]));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private void save() {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
//            for (Map.Entry<String, Boolean> entry : userLogin.entrySet()) {
//                bw.write(entry.getKey() + "," + entry.getValue());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public boolean existById(String identification) {
//        return userLogin.containsKey(identification);
//    }


    @Override
    public boolean login(UserAccount userAccount) {

        try (BufferedReader bin = Files.newBufferedReader(userCsvPath)) {
            String line;
            while ((line = bin.readLine()) != null) {
                String[] values = line.split(",");

                // Check if the ID and password match
                if (values[0].equals(userAccount.getIdentification()) && values[2].equals(userAccount.getPassword())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }

        // If no match is found, return false
        return false;
    }

    @Override
    public void logout(UserAccount user) {
        System.out.println(user);
    }

//    @Override
//    public UserAccount getById(String identification) {
//        return super.getById(identification);
//    }


}
