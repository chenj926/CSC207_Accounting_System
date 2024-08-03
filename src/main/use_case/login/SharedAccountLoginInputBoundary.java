package use_case.login;

/**
 * The SharedAccountLoginInputBoundary interface provides a method for executing login operations.
 * Implementations of this interface will handle the login process using the provided input data.
 *
 * @author Dana
 */
public interface SharedAccountLoginInputBoundary{

    /**
     * Executes the login process with the given input data.
     *
     * @param sharedAccountLoginInputData the input data required for the login process
     */
    void execute(SharedAccountLoginInputData sharedAccountLoginInputData);
}
