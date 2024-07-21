package interface_adaptors;

import use_case.LoginOutputBoundary;
import use_case.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel){
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData userInfo){
        // update the current login state
        LoginState loginState = loginViewModel.getState();
        loginState.setIdentification(userInfo.getIdentification());
        this.loginViewModel.setState(loginState);
        loginState.setSuccessMsg("Successfully Logged In!!!");
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveViewName(loginViewModel.getViewName());

        // should change to next view
        viewManagerModel.changeView("Transaction");
    }

    @Override
    public void prepareFailView(String err) {
        LoginState loginState = loginViewModel.getState();
        loginState.setStateError(err);
        loginState.setSuccessMsg(null); // Clear success message on failure
        loginViewModel.firePropertyChanged();
    }

}
