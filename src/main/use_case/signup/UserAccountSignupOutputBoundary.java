package use_case.signup;

/**
 * The UserAccountSignupOutputBoundary interface provides methods for preparing the view based on the outcome of a signup operation.
 * Implementations of this interface will handle the presentation logic for successful and failed signup attempts.
 *
 * @author Eric
 */
public interface UserAccountSignupOutputBoundary extends SignupOutputBoundary<UserAccountSignupOutputData> {

//    /**
//     * Prepares the success view with the given signup output data.
//     *
//     * @param user the signup output data containing user information and success status
//     */
//    void prepareSuccessView(UserAccountSignupOutputData user);
//
//    /**
//     * Prepares the fail view with the given error message.
//     *
//     * @param error the error message to be presented in case of a failed signup attempt
//     */
//    void prepareFailView(String error);
}