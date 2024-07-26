package interface_adaptors;

import use_case.Transaction.SignupOutputBoundary;
import use_case.Transaction.SignupOutputData;

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
     * @param response the signup output data containing user information and success status
     */
    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // update the current signup state
        SignupState signupState = signupViewModel.getState();
        signupState.setUsername(response.getUsername());
        this.signupViewModel.setState(signupState);
        signupState.setSuccessMsg("Sign up Successfully");
        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveViewName(signupViewModel.getViewName());

        // change to home page view
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