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
public class SharedAccountLoginPresenter extends AccountLoginPresenter<
        SharedAccountLoginViewModel,
        SharedAccountLoginOutputData,
        SharedAccountLoginState> implements SharedAccountLoginOutputBoundary {

    /**
     * Constructs a SharedAccountLoginPresenter object with the specified view manager model and shared account login view model.
     *
     * @param viewManagerModel            the view manager model to manage view transitions
     * @param sharedAccountLoginViewModel the shared account login view model to update the shared account login state
     */
    public SharedAccountLoginPresenter(ViewManagerModel viewManagerModel, SharedAccountLoginViewModel sharedAccountLoginViewModel) {
        super(viewManagerModel, sharedAccountLoginViewModel);
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
            SharedAccountLoginState sharedAccountLoginState = this.accountLoginViewModel.getState();
            sharedAccountLoginState.setIdentification(userInfo.getIdentification());
            this.viewManagerModel.setUserId(userInfo.getIdentification());
            this.accountLoginViewModel.setState(sharedAccountLoginState);
            sharedAccountLoginState.setSuccessMsg("Successfully Logged In to Shared Account!!!");
            this.accountLoginViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveViewName(this.accountLoginViewModel.getViewName());

            // Change to the next view
            this.viewManagerModel.changeView("Shared Account Homepage Two");
    }
}


