package use_case.login;

import data_access.account.AccountDataAccessInterface;

public abstract class LoginInteractor<
        T,
        O extends LoginOutputBoundary<LO>,
        LO extends LoginOutputData,
        I extends LoginInputData>{
    protected T userDataAccessObject;
    protected O presenter;
    protected LoginMediator mediator;

    public LoginInteractor(T userDataAccessObject, O presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    public abstract void execute(I inputData);

    /**
     * Sets the mediator for the interactor.
     *
     * @param mediator the LoginMediator instance to set
     */
    public void setMediator(LoginMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Checks if the provided password is valid (not null or empty).
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    public boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the provided identification is valid (not null or empty).
     *
     * @param id the identification to check
     * @return true if the identification is valid, false otherwise
     */
    public boolean checkIdentification(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return true;
    }
}
