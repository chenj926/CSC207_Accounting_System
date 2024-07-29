package use_case.login;

/**
 * The LoginInputData class represents the input data required for a login operation.
 *
 * @author Dana
 */
public class LoginInputData {
    private final String password;
    private final String identification;

    /**
     * Constructs a LoginInputData object with the specified password and identification.
     *
     * @param password       the password for the login
     * @param identification the identification for the login
     */
    public LoginInputData(String password, String identification){
        this.password = password;
        this.identification = identification;
    }

    /**
     * Gets the password for the login.
     *
     * @return the password for the login
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the identification for the login.
     *
     * @return the identification for the login
     */
    public String getIdentification() {
        return this.identification;
    }
}