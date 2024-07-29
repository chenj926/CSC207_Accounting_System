package interface_adaptors;

/**
 * The UserCreationFailed class represents an exception that is thrown when user creation fails.
 *
 * @author Xile
 */
public class UserCreationFailed extends RuntimeException {

    /**
     * Constructs a UserCreationFailed exception with the specified error message.
     *
     * @param error the error message
     */
    public UserCreationFailed(String error) {
        super(error);
    }
}