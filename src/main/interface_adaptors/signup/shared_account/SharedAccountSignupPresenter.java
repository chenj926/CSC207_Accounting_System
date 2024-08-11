package interface_adaptors.signup.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SignupPresenter;
import use_case.signup.shared_account.SharedAccountSignupOutputBoundary;
import use_case.signup.shared_account.SharedAccountSignupOutputData;

/**
 * The SharedAccountSignupPresenter class extends UserAccountSignupPresenter to handle specific logic for shared account signups.
 *
 * Authors:
 * - Xile
 * - Eric
 */
public class SharedAccountSignupPresenter extends SignupPresenter<
        SharedAccountSignupViewModel,
        SharedAccountSignupOutputData,
        SharedAccountSignupState>
        implements SharedAccountSignupOutputBoundary{

    /**
     * Constructs a SharedAccountSignupPresenter with the specified view manager model and shared account signup view model.
     *
     * @param viewManagerModel             the view manager model to manage view transitions
     * @param sharedAccountSignupViewModel the shared account signup view model to update the shared account signup state
     */
    public SharedAccountSignupPresenter(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedAccountSignupViewModel) {// Call parent constructor
        super(viewManagerModel, sharedAccountSignupViewModel);
    }

    /**
     * Prepares the success view specifically for shared account signup.
     * Updates the shared account signup state and changes the view as needed.
     *
     * @param data the shared account signup output data
     */
    @Override
    public void prepareSuccessView(SharedAccountSignupOutputData data) {
        SharedAccountSignupState signupState = accountSignupViewModel.getState();
        signupState.setSuccessMsg("Shared account signed up successfully.");
        accountSignupViewModel.firePropertyChanged();

        // Navigate to the home page after successful signup
        viewManagerModel.changeView("home page");
    }
}
