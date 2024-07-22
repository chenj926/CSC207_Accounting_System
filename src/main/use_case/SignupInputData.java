package use_case;

import java.util.*;

/**
 * The SignupInputData class represents the input data required for a signup operation.
 * It includes the username, password, and identification of the user.
 *
 * @author Eric
 */
public class SignupInputData {
    private final String username;
    private final String password;
    private final String identification;

    /**
     * Constructs a SignupInputData object with the specified username, password, and identification.
     *
     * @param username       the username of the user
     * @param password       the password of the user
     * @param identification the identification of the user
     */
    public SignupInputData(String username, String password, String identification){
        this.username = username;
        this.password = password;
        this.identification = identification;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the identification of the user.
     *
     * @return the identification of the user
     */
    public String getIdentification() {
        return this.identification;
    }
}