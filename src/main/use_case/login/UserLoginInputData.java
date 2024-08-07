package use_case.login;

/**
 * The LoginInputData class represents the input data required for a login operation.
 *
 * @author Dana
 */
public class UserLoginInputData extends LoginInputData {

    /**
     * Constructs a LoginInputData object with the specified password and identification.
     *
     * @param password       the password for the login
     * @param identification the identification for the login
     */
    public UserLoginInputData(String password, String identification){
        super(password,identification);
    }

}