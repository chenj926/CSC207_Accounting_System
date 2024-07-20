package interface_adaptors;

import use_case.LogoutOutputBoundary;
import use_case.LogoutOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogoutPresenter implements LogoutOutputBoundary {
    private final LogoutViewModel logoutViewModel;
    private ViewManagerModel viewManagerModel;

    public LogoutPresenter(LogoutViewModel logoutViewModel, ViewManagerModel viewManagerModel) {
        this.logoutViewModel = logoutViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // update the current  state
        LogoutState logoutState = logoutViewModel.getState();
        this.logoutViewModel.setState(logoutState);
        logoutState.setLogoutMessage("Successfully logged out");
        logoutViewModel.firePropertyChanged();
        viewManagerModel.setActiveViewName(logoutViewModel.getViewName());

        // change to signup view
        viewManagerModel.changeView("Sign up");
    }

    @Override public void prepareFailView(String message) {
        LogoutState logoutState = logoutViewModel.getState();
        logoutState.setLogoutMessage(message);
        logoutViewModel.firePropertyChanged();
    }
}

