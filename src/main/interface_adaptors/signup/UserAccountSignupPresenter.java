package interface_adaptors.signup;

import interface_adaptors.ViewManagerModel;
import use_case.signup.UserAccountSignupOutputBoundary;
import use_case.signup.UserAccountSignupOutputData;

/**
 * The UserAccountSignupPresenter class implements the UserAccountSignupOutputBoundary interface.
 * It handles the presentation logic for the standard signup process, updating the view model and managing view transitions.
 *
 * Authors:
 * - Xile
 * - Eric
 */
public class UserAccountSignupPresenter extends SignupPresenter<
        UserAccountSignupViewModel,
        UserAccountSignupOutputData,
        UserAccountSignupState>
        implements UserAccountSignupOutputBoundary {

    /**
     * Constructs a UserAccountSignupPresenter object for standard signups with the specified view manager model and signup view model.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param userAccountSignupViewModel  the signup view model to update the signup state
     */
    public UserAccountSignupPresenter(ViewManagerModel viewManagerModel, UserAccountSignupViewModel userAccountSignupViewModel) {
        super(viewManagerModel, userAccountSignupViewModel);
    }

    /**
     * Prepares the success view with the given signup output data.
     * Updates the signup state and changes the view to the home page.
     *
     * @param data the signup output data containing user information and success status
     */
    @Override
    public void prepareSuccessView(UserAccountSignupOutputData data) {
        if (!data.isUseCaseFailed()) {
            UserAccountSignupState userAccountSignupState = accountSignupViewModel.getState();
            userAccountSignupState.setSuccessMsg("User account signed up successfully.");
            accountSignupViewModel.setState(userAccountSignupState);
            accountSignupViewModel.firePropertyChanged();
        }

        // Navigate to the home page after successful signup
        viewManagerModel.changeView("home page");
    }
}
