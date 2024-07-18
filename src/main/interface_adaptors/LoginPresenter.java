package interface_adaptors;

import use_case.LoginOutputBoundary;
import use_case.LogInOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel){
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LogInOutputData userInfo){
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(userInfo.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveViewName(loginViewModel.getViewName());
    }

    @Override
    public void prepareFailView(String err) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(err);
        loginViewModel.firePropertyChanged();
    }

}
