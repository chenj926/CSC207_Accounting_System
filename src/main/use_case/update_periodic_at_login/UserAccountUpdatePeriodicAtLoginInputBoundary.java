package use_case.update_periodic_at_login;

/**
 * The UserAccountUpdatePeriodicAtLoginInputBoundary interface provides a method for executing periodic transaction updates.
 * Implementations of this interface will handle the update using the provided input data.
 *
 * @author Jessica
 */
public interface UserAccountUpdatePeriodicAtLoginInputBoundary {
    /**
     * Executes the periodic transaction update process with the given input data.
     *
     * @param userAccountUpdatePeriodicAtLoginInputData the input data required for the update process
     */
    void execute(UserAccountUpdatePeriodicAtLoginInputData userAccountUpdatePeriodicAtLoginInputData);
}
