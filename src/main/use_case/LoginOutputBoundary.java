package use_case;

/**
 * The LoginOutputBoundary interface provides methods for preparing the view based on the outcome of a login operation.
 * Implementations of this interface will handle the presentation logic for successful and failed login attempts.
 *
 * @author Dana
 * @author Eric
 */
public interface LoginOutputBoundary {

    /**
     * Prepares the success view with the given login output data.
     *
     * @param user the login output data containing user information and success status
     */
    void prepareSuccessView(LoginOutputData user);

    /**
     * Prepares the fail view with the given error message.
     *
     * @param error the error message to be presented in case of a failed login attempt
     */
    void prepareFailView(String error);
}