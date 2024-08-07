package data_access.authentication;

import data_access.account.CSVSharedAccountDataAccessObject;
import entity.account.SharedAccount;
import entity.account.UserAccount;
import use_case.transaction.one_time.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.SharedAccountPeriodicTransactionOutputData;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVSharedAccountLoginDataAccessObject extends CSVSharedAccountDataAccessObject implements SharedAccountLoginDataAccessInterface {

    /**
     * Constructs a new instance of {@code CSVUserLoginoutDataAccessObject}.
     * This constructor initializes the CSV path for shared accounts.
     *
     */
    public CSVSharedAccountLoginDataAccessObject() {
        super();
    }

    /**
     * Logs in a user by verifying their identification and password against the CSV data.
     * <p>
     * This method reads user data from a CSV file and checks if the provided user account's identification
     * and password match any entry in the file.
     * </p>
     *
     * @param sharedAccount the {@link SharedAccount} object containing the user's identification and password
     * @return {@code true} if the user is successfully logged in; {@code false} otherwise
     */
    @Override
    public boolean login(SharedAccount sharedAccount) {

        try (BufferedReader bin = Files.newBufferedReader(this.accountCsvPath)) {
            String line;
            while ((line = bin.readLine()) != null) {
                String[] values = line.split(",");

                // Check if the ID and password match
                if (values[0].equals(sharedAccount.getIdentification()) && values[2].equals(sharedAccount.getPassword())) {
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
