package interface_adaptors;

import use_case.*;

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
        LogoutState logoutState = logoutViewModel.getState();
        viewManagerModel.setActiveViewName(logoutViewModel.getViewName());
    }

    @Override public void prepareFailView(String message) {
        LogoutState logoutState = logoutViewModel.getState();
        logoutState.setLogoutMessage(message);
        logoutViewModel.firePropertyChanged();
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {

    }
}

