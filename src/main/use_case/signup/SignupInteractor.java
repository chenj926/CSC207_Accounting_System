package use_case.signup;

import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.UserAccount;
import entity.account.AccountFactory;

/**
 * The SignupInteractor class implements the SignupInputBoundary interface.
 * It handles the signup process by validating the input data, interacting with the data access layer,
 * and using the presenter to prepare the output views.
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class SignupInteractor implements SignupInputBoundary {
    final AccountFactory accountFactory;
    final SignupOutputBoundary presenter;
    final UserSignupDataAccessInterface userDataAccessObject;

    /**
     * Constructs a SignupInteractor object with the specified data access interface, output boundary, and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for user data
     * @param signupOutputBoundary          the output boundary for presenting the signup results
     * @param accountFactory                the factory for creating user accounts
     */
    public SignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.presenter = signupOutputBoundary;

    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param signupInputData the input data required for the signup process
     */
    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existById(signupInputData.getIdentification())) {
            // user already exist, return back fail view to presenter
            presenter.prepareFailView("User already exist!!!");
        }
        else {
            // check if username or password or id is valid (not empty)
            boolean validUsername = this.checkUsername(signupInputData.getUsername());
            boolean validPassword = this.checkPassword(signupInputData.getPassword());
            boolean validIdentificaiton = this.checkIdentification(signupInputData.getIdentification());


            if (!validUsername && !validPassword && !validIdentificaiton) {
                presenter.prepareFailView("Username AND Password AND Identification can not be empty!");
            } else if (!validUsername && !validPassword) {
                presenter.prepareFailView("Username AND Password can not be empty!");
            } else if (!validUsername && !validIdentificaiton) {
                presenter.prepareFailView("Username AND Identification can not be empty!");
            } else if (!validPassword && !validIdentificaiton) {
                presenter.prepareFailView("Password AND Identification can not be empty!");
            } else if(!validUsername) {
                presenter.prepareFailView("Username can not be empty!");
            } else if (!validPassword){
                presenter.prepareFailView("Password can not be empty!");
            } else if (!validIdentificaiton) {
                presenter.prepareFailView("Identification can not be empty!");
            } else {
                // create new user
                UserAccount newUser = accountFactory.createUserAccount(signupInputData.getUsername(),
                        signupInputData.getPassword(), signupInputData.getIdentification());
                userDataAccessObject.save(newUser);  // save this user

                // prepare output to presenter
                SignupOutputData signupOutputData = new SignupOutputData(newUser.getUsername(), false);
                presenter.prepareSuccessView(signupOutputData);
            }
        }
    }

    /**
     * Checks if the provided username is valid (not null or empty).
     *
     * @param username the username to check
     * @return true if the username is valid, false otherwise
     */
    private boolean checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the provided password is valid (not null or empty).
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    private boolean checkPassword(String password) {
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
    private boolean checkIdentification(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return true;
    }
}