package interface_adaptors.signup;

import interface_adaptors.ViewManagerModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import use_case.signup.SharedAccountSignupOutputData;
import use_case.signup.StandardSignupOutputData;

/**
 * The SignupPresenter class implements the SignupOutputBoundary interface.
 * It handles the presentation logic for the signup process, updating the view model and managing view transitions.
 *
 * @author Xile
 * @author Eric
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a SignupPresenter object with the specified view manager model and signup view model.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param signupViewModel  the signup view model to update the signup state
     */
    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel) {
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
    public void prepareSuccessView(SignupOutputData data) {
        SignupState signupState = signupViewModel.getState();

        if (data instanceof SharedAccountSignupOutputData) {
            SharedAccountSignupOutputData sharedData = (SharedAccountSignupOutputData) data;

            // Check if the shared account already exists
            if (sharedData.isSharedAccountExists()) {
                // Set a specific success message indicating user input is needed
                signupState.setSuccessMsg("Shared account already exists. Prompt user for action.");
            } else {
                // If shared account doesn't exist, mark success
                signupState.setSuccessMsg("Shared account signed up successfully.");
            }
        } else {
            // For StandardSignupOutputData
            if (!data.isUseCaseFailed()) {
                signupState.setSuccessMsg("User account signed up successfully.");
            }
        }

        signupViewModel.firePropertyChanged();
        // After success, navigate back to the main menu
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
        this.signupViewModel.setState(signupState);
        signupState.setStateError(error);
        signupState.setSuccessMsg(null); // Clear success message on failure
        signupViewModel.firePropertyChanged();

    }
}