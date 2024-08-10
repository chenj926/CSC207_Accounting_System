package data_access.authentication;

import data_access.account.CSVUserAccountDataAccessObject;
import entity.account.SharedAccount;
import entity.account.UserAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A CSV-based implementation of data access for user login and logout operations.
 * <p>
 * This class extends {@link CSVUserAccountDataAccessObject} and implements both {@link LogoutDataAccessInterface}
 * and {@link LoginDataAccessInterface}. It provides methods to check user login status and handle user logout,
 * utilizing a CSV file for data storage.
 * </p>
 * <p>
 * This class also includes login functionality for shared accounts.
 * </p>
 *
 * @author Jessica Chen
 * @author Eric Chen
 */
public class CSVUserLoginDataAccessObject extends CSVUserAccountDataAccessObject implements LoginDataAccessInterface {
    /**
     * Constructs a new instance of {@code CSVUserLoginoutDataAccessObject}.
     * Initializes the CSV-based data access object for user login and logout operations.
     */
    public CSVUserLoginDataAccessObject() {
        super();
    }

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

        try (BufferedReader bin = Files.newBufferedReader(this.accountCsvPath)) {
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
}
