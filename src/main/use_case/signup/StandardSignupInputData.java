package use_case.signup;

/**
 * The StandardSignupInputData class represents the input data for a standard user signup operation.
 * It extends the basic SignupInputData class without additional fields.
 */
public class StandardSignupInputData extends SignupInputData {

    /**
     * Constructs a StandardSignupInputData object with the specified username, password, and identification.
     *
     * @param username       the username of the user
     * @param password       the password of the user
     * @param identification the identification of the user
     */
    public StandardSignupInputData(String username, String password, String identification) {
        super(username, password, identification);
    }
}

