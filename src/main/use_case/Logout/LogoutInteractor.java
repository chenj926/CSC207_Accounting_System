package use_case.Logout;

import entity.UserAccount;
import data_access.LogoutDataAccessInterface;

/**
 * The LogoutInteractor class implements the LogoutInputBoundary interface.
 * It handles the logout process by interacting with the data access layer and the presenter.
 *
 * @author Dana
 */
public class LogoutInteractor implements LogoutInputBoundary {
    final LogoutDataAccessInterface userDataAccessObject;
    final LogoutOutputBoundary presenter;

    /**
     * Constructs a LogoutInteractor object with the specified data access interface and output boundary.
     *
     * @param userDataAccessObject the data access interface for user data
     * @param presenter            the output boundary for presenting the logout results
     */
    public LogoutInteractor(LogoutDataAccessInterface userDataAccessObject, LogoutOutputBoundary presenter) {
        this.presenter = presenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    /**
     * Executes the logout process with the given input data.
     *
     * @param logoutInputData the input data required for the logout process
     */
    @Override
    public void execute(LogoutInputData logoutInputData) {
        //Assume user already logged in
        UserAccount userAccount = userDataAccessObject.getById(logoutInputData.getLogout());
        userDataAccessObject.logout(userAccount);
        // prepare output to presenter
        LogoutOutputData logoutOutputData = new LogoutOutputData(logoutInputData.getLogout());
        presenter.prepareSuccessView(logoutOutputData);


    }
}