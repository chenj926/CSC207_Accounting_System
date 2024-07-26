package use_case.Logout;

/**
 * The LogoutOutputBoundary interface provides methods for preparing the view based on the outcome of a logout operation.
 * Implementations of this interface will handle the presentation logic for successful and failed logout attempts.
 *
 * @author Dana
 */
public interface LogoutOutputBoundary {

    /**
     * Prepares the success view with the given logout output data.
     *
     * @param user the logout output data containing user information and success status
     */
    void prepareSuccessView(LogoutOutputData user);

    /**
     * Prepares the fail view with the given error message.
     *
     * @param error the error message to be presented in case of a failed logout attempt
     */
    void prepareFailView(String error);
}
