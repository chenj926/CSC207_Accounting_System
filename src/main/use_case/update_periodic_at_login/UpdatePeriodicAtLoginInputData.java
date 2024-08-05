package use_case.update_periodic_at_login;

import java.time.LocalDate;

/**
 * The UpdatePeriodicAtLoginInputData class represents the input data required for updating periodic transaction at login operation.
 * It includes details such as the user identification and the current date.
 *
 * @author Jessica
 */
public class UpdatePeriodicAtLoginInputData {
    private String identification;
    private LocalDate currentDate;

    /**
     * Constructs a UpdatePeriodicAtLoginInputData object with the specified details.
     *
     * @param identification identification of the user
     * @param currentDate date of the login
     */
    public UpdatePeriodicAtLoginInputData(String identification, LocalDate currentDate) {
        this.identification = identification;
        this.currentDate = currentDate;
    }

    /**
     * Gets the identification of the user.
     *
     * @return identification of the user
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Gets current date of the login.
     *
     * @return the current date of the login
     */
    public LocalDate getCurrentDate() {
        return currentDate;
    }
}
