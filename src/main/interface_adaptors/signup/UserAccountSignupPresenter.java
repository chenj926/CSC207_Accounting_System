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
public class UserAccountSignupPresenter implements UserAccountSignupOutputBoundary {
    protected final ViewManagerModel viewManagerModel;
    protected final SignupViewModel signupViewModel;

    /**
     * Constructs a UserAccountSignupPresenter object for standard signups with the specified view manager model and signup view model.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param signupViewModel  the signup view model to update the signup state
     */
    public UserAccountSignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
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
            SignupState signupState = signupViewModel.getState();
            signupState.setSuccessMsg("User account signed up successfully.");
            signupViewModel.firePropertyChanged();
        }

        // Navigate to the home page after successful signup
        viewManagerModel.changeView("home page");
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the signup state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed signup attempt
     */
    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setStateError(error);
        signupState.setSuccessMsg(null); // Clear success message on failure
        signupViewModel.firePropertyChanged();
    }
}
