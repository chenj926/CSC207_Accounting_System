package interface_adaptors.login;

import interface_adaptors.ViewManagerModel;
import use_case.login.LoginOutputData;
import use_case.login.UserLoginOutputBoundary;
import use_case.login.UserLoginOutputData;

/**
 * The LoginPresenter class implements the LoginOutputBoundary interface.
 * It handles the presentation logic for the login process, updating the view model and managing view transitions.
 *
 * @author Eric Chen
 */
public class UserLoginPresenter implements UserLoginOutputBoundary {
    private final UserLoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a LoginPresenter object with the specified view manager model and login view model.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param loginViewModel   the login view model to update the login state
     */
    public UserLoginPresenter(ViewManagerModel viewManagerModel,
                          UserLoginViewModel loginViewModel){
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
    public void prepareSuccessView(UserLoginOutputData userInfo){
        // update the current login state
        LoginState loginState = this.loginViewModel.getState();
        loginState.setIdentification(userInfo.getIdentification());
        this.viewManagerModel.setUserId(userInfo.getIdentification());
        this.loginViewModel.setState(loginState);
        loginState.setSuccessMsg("Successfully Logged In!!!");
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
        LoginState loginState = this.loginViewModel.getState();
        loginState.setStateError(err);
        loginState.setSuccessMsg(null); // Clear success message on failure
        this.loginViewModel.firePropertyChanged();
    }

}
