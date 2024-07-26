package interface_adaptors.logout;

import interface_adaptors.ViewManagerModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The LogoutPresenter class implements the LogoutOutputBoundary interface.
 * It handles the presentation logic for the logout process, updating the view model and managing view transitions.
 *
 * @author Dana
 */
public class LogoutPresenter implements LogoutOutputBoundary {
    private final LogoutViewModel logoutViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a LogoutPresenter object with the specified logout view model and view manager model.
     *
     * @param logoutViewModel the logout view model to update the logout state
     * @param viewManagerModel the view manager model to manage view transitions
     */
    public LogoutPresenter(LogoutViewModel logoutViewModel, ViewManagerModel viewManagerModel) {
        this.logoutViewModel = logoutViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    /**
     * Prepares the success view with the given logout output data.
     * Updates the logout state and changes the view to the signup view.
     *
     * @param response the logout output data containing logout information and success status
     */
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

    /**
     * Prepares the fail view with the given error message.
     * Updates the logout state with the error message.
     *
     * @param stateError the error message to be presented in case of a failed logout attempt
     */
    @Override
    public void prepareFailView(String stateError) {
        LogoutState logoutState = logoutViewModel.getState();
        logoutState.setStateError(stateError);
        logoutViewModel.firePropertyChanged();
    }
}

