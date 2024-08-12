package interface_adaptors.login.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.LoginPresenter;
import use_case.login.user_account.UserAcountLoginOutputBoundary;
import use_case.login.user_account.UserAccountLoginOutputData;

/**
 * The LoginPresenter class implements the LoginOutputBoundary interface.
 * It handles the presentation logic for the login process, updating the view model and managing view transitions.
 *
 * @author Eric Chen
 */
public class UserAccountLoginPresenter extends LoginPresenter<UserAccountLoginViewModel,
        UserAccountLoginOutputData,
        UserAccountLoginState> implements UserAcountLoginOutputBoundary {

    /**
     * Constructs a LoginPresenter object with the specified view manager model and login view model.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param userAccountLoginViewModel   the login view model to update the login state
     */
    public UserAccountLoginPresenter(ViewManagerModel viewManagerModel,
                                     UserAccountLoginViewModel userAccountLoginViewModel){
        super(viewManagerModel, userAccountLoginViewModel);
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
        UserAccountLoginState userAccountLoginState = this.accountLoginViewModel.getState();
        userAccountLoginState.setIdentification(userInfo.getIdentification());

//        UserAccountHomepageTwoViewModel homepageTwoViewModel = (UserAccountHomepageTwoViewModel) this.viewManagerModel.getViewModel("Homepage Two");
//        UserAccountHomepageTwoState homepageTwoState = homepageTwoViewModel.getState();
//        homepageTwoState.setId(userInfo.getIdentification());
//        homepageTwoViewModel.setState(homepageTwoState);
//        this.viewManagerModel.updateViewModel("Homepage Two", homepageTwoViewModel);

        System.out.println("in login"+userInfo.getIdentification());
        this.viewManagerModel.setUserId(userInfo.getIdentification());
        this.accountLoginViewModel.setState(userAccountLoginState);
        userAccountLoginState.setSuccessMsg("Successfully Logged In!!!");
        this.accountLoginViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveViewName(this.accountLoginViewModel.getViewName());

        // should change to next view
        this.viewManagerModel.changeView("Homepage Two");
    }

}
