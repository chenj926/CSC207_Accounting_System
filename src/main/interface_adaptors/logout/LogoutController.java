package interface_adaptors.logout;

import use_case.Logout.LogoutInputBoundary;
import use_case.Logout.LogoutInputData;

/**
 * The LogoutController class is responsible for handling user interactions related to the logout process.
 * It communicates with the use case interactor to execute the logout process.
 *
 * @author Dana
 */
public class LogoutController {
    final LogoutInputBoundary logoutInteractor;

    /**
     * Constructs a LogoutController object with the specified use case interactor.
     *
     * @param logoutInteractor the use case interactor for user logout
     */
    public LogoutController(LogoutInputBoundary logoutInteractor) {
        this.logoutInteractor = logoutInteractor;
    }

    /**
     * Executes the logout process with the given logout details.
     *
     * @param logout the logout details
     */
    public void execute(String logout) {
        LogoutInputData logoutInputData = new LogoutInputData(logout);

        logoutInteractor.execute(logoutInputData);
    }
}