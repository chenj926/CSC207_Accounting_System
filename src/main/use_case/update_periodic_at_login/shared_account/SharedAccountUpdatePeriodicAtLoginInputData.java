package use_case.update_periodic_at_login.shared_account;

import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

public class SharedAccountUpdatePeriodicAtLoginInputData extends AccountUpdatePeriodicAtLoginInputData {
    /**
     * Constructs a UserAccountUpdatePeriodicAtLoginInputData object with the specified details.
     *
     * @param identification identification of the user
     * @param currentDate date of the login
     */
    public SharedAccountUpdatePeriodicAtLoginInputData(String identification, LocalDate currentDate) {
        super(identification, currentDate);
    }
}
