package interface_adaptors;

import use_case.LogoutInputBoundary;
import use_case.LogoutInputData;
import use_case.LogoutInteractor;

public class LogoutController {
    final LogoutInputBoundary logoutInteractor;
    public LogoutController(LogoutInputBoundary logoutInteractor) {
        this.logoutInteractor = logoutInteractor;
    }

    public void execute(String userId) {
        LogoutInputData logoutInputData = new LogoutInputData(userId);

        logoutInteractor.execute(logoutInputData);
    }
}