package use_case.login;

import entity.account.UserAccount;
import data_access.authentication.LoginDataAccessInterface;

/**
 * The LoginInteractor class implements the LoginInputBoundary interface.
 * It handles the login process by validating the input data and interacting with the data access layer and the presenter.
 *
 * @author Dana
 * @author Eric
 */
public class LoginInteractor implements LoginInputBoundary {
    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary presenter;
    private final LoginMediator mediator;

    /**
     * Constructs a LoginInteractor object with the specified data access interface and output boundary.
     *
     * @param LoginDataAccessInterface the data access interface for user data
     * @param logInOutputBoundary      the output boundary for presenting the login results
     */
    public LoginInteractor(LoginDataAccessInterface LoginDataAccessInterface, LoginOutputBoundary logInOutputBoundary, LoginMediator mediator) {
        this.userDataAccessObject = LoginDataAccessInterface;
        this.presenter = logInOutputBoundary;
        this.mediator = mediator;
    }

    /**
     * Executes the login process with the given input data.
     *
     * @param loginInputData the input data required for the login process
     */
    @Override
    public void execute(LoginInputData loginInputData) {

            // check if username or password or id is valid (not empty)
            boolean validPassword = this.checkPassword(loginInputData.getPassword());
            boolean validIdentificaiton = this.checkIdentification(loginInputData.getIdentification());

            if (!validPassword && !validIdentificaiton) {
                presenter.prepareFailView("Identification AND Password can not be empty!");
                return;
            } else if (!validPassword) {
                presenter.prepareFailView("Password can not be empty!");
                return;
            } else if (!validIdentificaiton) {
                presenter.prepareFailView("Identification can not be empty!");
                return;
            } else {
                if (!userDataAccessObject.existById(loginInputData.getIdentification())) {
                    //User does not exist
                    presenter.prepareFailView("User not found");
                    return;
                }

                //User exists, fetch the user account
                UserAccount userAccount = userDataAccessObject.getById(loginInputData.getIdentification());
                if (userAccount != null && !userAccount.getPassword().equals(loginInputData.getPassword())) {
                    presenter.prepareFailView("Incorrect Password!");
                    return;
                }

                // attempt to login
                boolean isLogin = userDataAccessObject.login(userAccount);

                // if login failed, password wrong
                if (!isLogin) {
                    presenter.prepareFailView("Incorrect Password!");
                } else {
                    // prepare output to presenter
                    LoginOutputData loginOutputData = new LoginOutputData(userAccount.getIdentification(), true);
                    presenter.prepareSuccessView(loginOutputData);
                    // Notify mediator on successful login
                    mediator.notifyLoginResult(true, userAccount.getIdentification());
                    }
                }

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
