package interface_adaptors.login;

import interface_adaptors.ViewManagerModel;
import use_case.login.UserAcountLoginOutputBoundary;
import use_case.login.UserAccountLoginOutputData;

/**
 * The LoginPresenter class implements the LoginOutputBoundary interface.
 * It handles the presentation logic for the login process, updating the view model and managing view transitions.
 *
 * @author Eric Chen
 */
public class UserAcountLoginPresenter implements UserAcountLoginOutputBoundary {
    private final UserAccountLoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a LoginPresenter object with the specified view manager model and login view model.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param loginViewModel   the login view model to update the login state
     */
    public UserAcountLoginPresenter(ViewManagerModel viewManagerModel,
                                    UserAccountLoginViewModel loginViewModel){
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view with the given login output data.
     * Updates the login state and changes the view to the transaction view.
     *
     * @param userInfo the login output data containing user information and success status
     */
    @Override
    public void prepareSuccessView(UserAccountLoginOutputData userInfo){
        // update the current login state
        UserAccountLoginState userAccountLoginState = this.loginViewModel.getState();
        userAccountLoginState.setIdentification(userInfo.getIdentification());

//        HomepageTwoViewModel homepageTwoViewModel = (HomepageTwoViewModel) this.viewManagerModel.getViewModel("Homepage Two");
//        HomepageTwoState homepageTwoState = homepageTwoViewModel.getState();
//        homepageTwoState.setId(userInfo.getIdentification());
//        homepageTwoViewModel.setState(homepageTwoState);
//        this.viewManagerModel.updateViewModel("Homepage Two", homepageTwoViewModel);

        this.viewManagerModel.setUserId(userInfo.getIdentification());
        this.loginViewModel.setState(userAccountLoginState);
        userAccountLoginState.setSuccessMsg("Successfully Logged In!!!");
        this.loginViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveViewName(this.loginViewModel.getViewName());

        // should change to next view
        this.viewManagerModel.changeView("Homepage Two");
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the login state with the error message and clears the success message.
     *
     * @param err the error message to be presented in case of a failed login attempt
     */
    @Override
    public void prepareFailView(String err) {
        UserAccountLoginState userAccountLoginState = this.loginViewModel.getState();
        userAccountLoginState.setStateError(err);
        userAccountLoginState.setSuccessMsg(null); // Clear success message on failure
        this.loginViewModel.firePropertyChanged();
    }

}
