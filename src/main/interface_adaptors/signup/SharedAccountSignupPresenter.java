package interface_adaptors.signup;

import interface_adaptors.ViewManagerModel;
import use_case.signup.SignupOutputData;
import use_case.signup.SharedAccountSignupOutputData;

/**
 * The SharedAccountSignupPresenter class extends SignupPresenter to handle specific logic for shared account signups.
 *
 * Authors:
 * - Xile
 * - Eric
 */
public class SharedAccountSignupPresenter extends SignupPresenter {
    private final SharedAccountSignupViewModel sharedAccountSignupViewModel;

    /**
     * Constructs a SharedAccountSignupPresenter with the specified view manager model and shared account signup view model.
     *
     * @param viewManagerModel             the view manager model to manage view transitions
     * @param sharedAccountSignupViewModel the shared account signup view model to update the shared account signup state
     */
    public SharedAccountSignupPresenter(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedAccountSignupViewModel) {
        super(viewManagerModel, sharedAccountSignupViewModel); // Call parent constructor
        this.sharedAccountSignupViewModel = sharedAccountSignupViewModel;
    }

    /**
     * Prepares the success view specifically for shared account signup.
     * Updates the shared account signup state and changes the view as needed.
     *
     * @param data the shared account signup output data
     */
    @Override
    public void prepareSuccessView(SignupOutputData data) {
        if (data instanceof SharedAccountSignupOutputData) {
            SharedAccountSignupOutputData sharedData = (SharedAccountSignupOutputData) data;

            SignupState signupState = sharedAccountSignupViewModel.getState();

            if (sharedData.isSharedAccountExists()) {
                signupState.setSuccessMsg("Shared account already exists. Prompt user for further actions.");
            } else {
                signupState.setSuccessMsg("Shared account signed up successfully.");
            }
            sharedAccountSignupViewModel.firePropertyChanged();
        }

        // Navigate to the home page or another view specific for shared accounts after successful signup
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
        SignupState signupState = sharedAccountSignupViewModel.getState();
        signupState.setStateError(error);
        signupState.setSuccessMsg(null); // Clear success message on failure
        sharedAccountSignupViewModel.firePropertyChanged();
    }
}
