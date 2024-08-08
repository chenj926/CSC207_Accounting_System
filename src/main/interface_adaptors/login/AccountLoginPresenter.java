package interface_adaptors.login;

import interface_adaptors.ViewManagerModel;
import use_case.login.LoginOutputData;

public abstract class AccountLoginPresenter<
        V extends AccountLoginViewModel,
        O extends LoginOutputData,
        S extends AccountLoginState>{
    protected final ViewManagerModel viewManagerModel;
    protected final  V accountLoginViewModel;

    public AccountLoginPresenter(ViewManagerModel viewManagerModel, V accountLoginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountLoginViewModel = accountLoginViewModel;
    }

    public abstract void prepareSuccessView(O userInfo);

    /**
     * Prepares the fail view with the given error message.
     * Updates the login state with the error message and clears the success message.
     *
     * @param err the error message to be presented in case of a failed login attempt
     */
    public void prepareFailView(String err){
        S accountLoginState = (S) this.accountLoginViewModel.getState();
        accountLoginState.setStateError(err);
        accountLoginState.setSuccessMsg(null); // Clear success message on failure
        this.accountLoginViewModel.firePropertyChanged();

    }


}
