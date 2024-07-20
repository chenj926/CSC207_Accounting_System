package interface_adaptors;

import use_case.SignupOutputBoundary;
import use_case.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // update the current signup state
        SignupState signupState = signupViewModel.getState();
        signupState.setUsername(response.getUsername());
        this.signupViewModel.setState(signupState);
        signupState.setSuccessMsg("Sign up Successfully");
        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveViewName(signupViewModel.getViewName());

        // change to log in view
        viewManagerModel.changeView("log in");
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        this.signupViewModel.setState(signupState);
        signupState.setStateError(error);
        signupViewModel.firePropertyChanged();

    }
}