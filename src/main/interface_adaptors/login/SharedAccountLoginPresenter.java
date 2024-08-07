package interface_adaptors.login;

import interface_adaptors.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.SharedAccountLoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.login.SharedAccountLoginOutputData;

/**
 * The SharedAccountLoginPresenter class implements the LoginOutputBoundary interface.
 * It handles the presentation logic specifically for shared account login.
 *
 * @author Xile Chen, Eric Chen
 */
public class SharedAccountLoginPresenter implements SharedAccountLoginOutputBoundary {
    private final SharedAccountLoginViewModel sharedAccountLoginViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a SharedAccountLoginPresenter object with the specified view manager model and shared account login view model.
     *
     * @param viewManagerModel            the view manager model to manage view transitions
     * @param sharedAccountLoginViewModel the shared account login view model to update the shared account login state
     */
    public SharedAccountLoginPresenter(ViewManagerModel viewManagerModel, SharedAccountLoginViewModel sharedAccountLoginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.sharedAccountLoginViewModel = sharedAccountLoginViewModel;
    }

    /**
     * Prepares the success view with the given shared account login output data.
     * Updates the shared account login state and changes the view to the transaction view.
     *
     * @param userInfo the login output data containing user information and success status
     */
    @Override
    public void prepareSuccessView(SharedAccountLoginOutputData userInfo) {
            // Handle shared account login
            SharedAccountLoginState sharedAccountLoginState = this.sharedAccountLoginViewModel.getState();
            sharedAccountLoginState.setSharedAccountId(userInfo.getSharedAccountId());
            this.viewManagerModel.setSharedAccountId(userInfo.getSharedAccountId());
            this.sharedAccountLoginViewModel.setState(sharedAccountLoginState);
            sharedAccountLoginState.setSuccessMsg("Successfully Logged In to Shared Account!!!");
            this.sharedAccountLoginViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveViewName(this.sharedAccountLoginViewModel.getViewName());

            // Change to the next view
            this.viewManagerModel.changeView("Homepage Two");
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the shared account login state with the error message and clears the success message.
     *
     * @param err the error message to be presented in case of a failed login attempt
     */
    @Override
    public void prepareFailView(String err) {
        SharedAccountLoginState sharedAccountLoginState = this.sharedAccountLoginViewModel.getState();
        sharedAccountLoginState.setStateError(err);
        sharedAccountLoginState.setSuccessMsg(null); // Clear success message on failure
        this.sharedAccountLoginViewModel.firePropertyChanged();
    }
}


