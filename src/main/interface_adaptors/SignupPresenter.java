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
        // On success, switch to the login view.
        System.out.println("prepareSuccessView called with response: " + response.getUsername());

        SignupState signupState = signupViewModel.getState();
//        LoginState loginState = loginViewModel.getState();
        signupState.setUsername(response.getUsername());
        this.signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveViewName(signupViewModel.getViewName());
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("prepareFailView called with error: " + error);
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}