package interface_adaptors;

import use_case.LogoutInputBoundary;
import use_case.LogoutInputData;

public class LogoutController {
    final LogoutInputBoundary logoutInteractor;
    public LogoutController(LogoutInputBoundary logoutInteractor) {
        this.logoutInteractor = logoutInteractor;
    }

    public void execute(String logout) {
        LogoutInputData logoutInputData = new LogoutInputData(logout);

        logoutInteractor.execute(logoutInputData);
    }
}