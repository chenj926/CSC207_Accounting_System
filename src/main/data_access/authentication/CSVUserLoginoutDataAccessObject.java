package data_access.authentication;

import data_access.account.CSVUserAccountDataAccessObject;
import entity.UserAccount;

import java.nio.file.Files;
import java.io.*;

/**
 * A CSV-based implementation of data access for user login and logout operations.
 * <p>
 * This class extends {@link CSVUserAccountDataAccessObject} and implements both {@link LogoutDataAccessInterface}
 * and {@link LoginDataAccessInterface}. It provides methods to check user login status and handle user logout,
 * utilizing a CSV file for data storage.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class CSVUserLoginoutDataAccessObject extends CSVUserAccountDataAccessObject implements LogoutDataAccessInterface, LoginDataAccessInterface {
    private static final String FILE_PATH = CSVUserAccountDataAccessObject.USER_CSV_FILE_PATH;
//    private Map<String, Boolean> userLogin;

    /**
     * Constructs a new instance of {@code CSVUserLoginoutDataAccessObject}.
     * Initializes the CSV-based data access object for user login and logout operations.
     */
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


    /**
     * Logs in a user by verifying their identification and password against the CSV data.
     * <p>
     * This method reads user data from a CSV file and checks if the provided user account's identification
     * and password match any entry in the file.
     * </p>
     *
     * @param userAccount the {@link UserAccount} object containing the user's identification and password
     * @return {@code true} if the user is successfully logged in; {@code false} otherwise
     */
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

    /**
     * Handles the logout operation for the specified user.
     * <p>
     * This method is a placeholder in the CSV implementation and does not currently persist logout information
     * to the CSV file.
     * </p>
     *
     * @param user the {@link UserAccount} object representing the user who is logging out
     */
    @Override
    public void logout(UserAccount user) {
        System.out.println(user);
    }

//    @Override
//    public UserAccount getById(String identification) {
//        return super.getById(identification);
//    }


}
