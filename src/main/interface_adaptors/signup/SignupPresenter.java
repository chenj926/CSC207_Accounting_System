package interface_adaptors.signup;

import interface_adaptors.ViewManagerModel;
import use_case.signup.SignupOutputData;

public abstract class SignupPresenter<
        V extends SignupViewModel,
        O extends SignupOutputData,
        S extends SignupState> {
    protected final ViewManagerModel viewManagerModel;
    protected final V accountSignupViewModel;

    /**
     * Constructs a UserAccountSignupPresenter object for standard signups with the specified view manager model and signup view model.
     *
     * @param viewManagerModel the view manager model to manage view transitions
     * @param accountSignupViewModel  the signup view model to update the signup state
     */
    public SignupPresenter(ViewManagerModel viewManagerModel, V accountSignupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountSignupViewModel = accountSignupViewModel;
    }

    public abstract void prepareSuccessView(O accountSignupViewModel);

    /**
     * Prepares the fail view with the given error message.
     * Updates the signup state with the error message and clears the success message.
     *
     * @param error the error message to be presented in case of a failed signup attempt
     */
    public void prepareFailView(String error) {
        S accountSignupState = (S) accountSignupViewModel.getState();
        accountSignupState.setStateError(error);
        accountSignupState.setSuccessMsg(null); // Clear success message on failure
        accountSignupViewModel.firePropertyChanged();
    }
}
