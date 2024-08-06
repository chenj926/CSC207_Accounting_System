package use_case.update_periodic_at_login;

/**
 * The UpdatePeriodicAtLoginInputBoundary interface provides a method for executing periodic transaction updates.
 * Implementations of this interface will handle the update using the provided input data.
 *
 * @author Jessica
 */
public interface UpdatePeriodicAtLoginInputBoundary {
    /**
     * Executes the periodic transaction update process with the given input data.
     *
     * @param updatePeriodicAtLoginInputData the input data required for the update process
     */
    void execute(UpdatePeriodicAtLoginInputData updatePeriodicAtLoginInputData);
}
