package use_case;

import java.util.*;

/**
 * The SignupInputBoundary interface provides a method for executing signup operations.
 * Implementations of this interface will handle the signup process using the provided input data.
 *
 * @author Eric
 * @author Dana
 */
public interface SignupInputBoundary {

    /**
     * Executes the signup process with the given input data.
     *
     * @param signupInputData the input data required for the signup process
     */
    void execute(SignupInputData signupInputData);
}