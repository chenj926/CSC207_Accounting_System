package use_case.login;

/**
 * The LoginInputBoundary interface provides a method for executing login operations.
 * Implementations of this interface will handle the login process using the provided input data.
 *
 * @author Dana
 */
public interface LoginInputBoundary {

    /**
     * Executes the login process with the given input data.
     *
     * @param loginInputData the input data required for the login process
     */
    void execute(LoginInputData loginInputData);
}