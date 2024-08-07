package interface_adaptors.signup;

import interface_adaptors.ViewManagerModel;
import use_case.signup.SharedAccountSignupOutputBoundary;
import use_case.signup.SharedAccountSignupOutputData;

/**
 * The SharedAccountSignupPresenter class extends UserAccountSignupPresenter to handle specific logic for shared account signups.
 *
 * Authors:
 * - Xile
 * - Eric
 */
public class SharedAccountSignupPresenter implements SharedAccountSignupOutputBoundary{
    private final ViewManagerModel viewManagerModel;
    private final SharedAccountSignupViewModel sharedAccountSignupViewModel;

    /**
     * Constructs a SharedAccountSignupPresenter with the specified view manager model and shared account signup view model.
     *
     * @param viewManagerModel             the view manager model to manage view transitions
     * @param sharedAccountSignupViewModel the shared account signup view model to update the shared account signup state
     */
    public SharedAccountSignupPresenter(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedAccountSignupViewModel) {// Call parent constructor
        this.viewManagerModel = viewManagerModel;
        this.sharedAccountSignupViewModel = sharedAccountSignupViewModel;
    }

    /**
     * Prepares the success view specifically for shared account signup.
     * Updates the shared account signup state and changes the view as needed.
     *
     * @param data the shared account signup output data
     */
    @Override
    public void prepareSuccessView(SharedAccountSignupOutputData data) {
        SharedAccountSignupState signupState = sharedAccountSignupViewModel.getState();
        signupState.setSuccessMsg("Shared account signed up successfully.");
        sharedAccountSignupViewModel.firePropertyChanged();

        // Navigate to the home page after successful signup
        viewManagerModel.changeView("home page");
    }

    /**
     * Prepares the fail view with the given error message for shared account signup.
     * Updates the shared account signup state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed shared account signup attempt
     */
    @Override
    public void prepareFailView(String error) {
        SharedAccountSignupState signupState = sharedAccountSignupViewModel.getState();
        signupState.setStateError(error);
        signupState.setSuccessMsg(null); // Clear success message on failure
        sharedAccountSignupViewModel.firePropertyChanged();
    }
}
