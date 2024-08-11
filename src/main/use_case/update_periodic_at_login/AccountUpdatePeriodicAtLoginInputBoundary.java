package use_case.update_periodic_at_login;

public interface AccountUpdatePeriodicAtLoginInputBoundary<D extends AccountUpdatePeriodicAtLoginInputData> {
    /**
     * Executes the periodic transaction update process with the given input data.
     *
     * @param accountUpdatePeriodicAtLoginInputData the input data required for the update process
     */
    void execute(D accountUpdatePeriodicAtLoginInputData);
}
