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
        // update the current signup state
//        LogoutState logoutState = logoutViewModel.getState();
//        logoutState.setIdentification();
//        this.logoutViewModel.setState(logoutState);
//        logoutState.setSuccessMsg("Sign up Success");
//        logoutViewModel.firePropertyChanged();
//        viewManagerModel.setActiveViewName(logoutViewModel.getViewName());
//
//        // change to log in view
//        viewManagerModel.changeView("log in");
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

