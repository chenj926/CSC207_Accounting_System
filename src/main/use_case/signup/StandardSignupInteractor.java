package use_case.signup;

import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.UserAccount;
import entity.account.AccountFactory;

/**
 * The StandardSignupInteractor class implements the SignupInputBoundary interface.
 * It handles the signup process for standard user accounts by validating the input data,
 * interacting with the data access layer, and using the presenter to prepare the output views.
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class StandardSignupInteractor implements SignupInputBoundary {
    final AccountFactory accountFactory;
    final SignupOutputBoundary presenter;
    final UserSignupDataAccessInterface userDataAccessObject;

    /**
     * Constructs a StandardSignupInteractor object with the specified data access interface, output boundary, and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for user data
     * @param signupOutputBoundary          the output boundary for presenting the signup results
     * @param accountFactory                the factory for creating user accounts
     */
    public StandardSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
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
            // User already exists, return fail view
            presenter.prepareFailView("User already exists!!!");
        } else {
            // Check if username, password, or identification is valid (not empty)
            boolean validUsername = this.checkUsername(signupInputData.getUsername());
            boolean validPassword = this.checkPassword(signupInputData.getPassword());
            boolean validIdentification = this.checkIdentification(signupInputData.getIdentification());

            if (!validUsername && !validPassword && !validIdentification) {
                presenter.prepareFailView("Username, Password, and Identification cannot be empty!");
            } else if (!validUsername && !validPassword) {
                presenter.prepareFailView("Username and Password cannot be empty!");
            } else if (!validUsername && !validIdentification) {
                presenter.prepareFailView("Username and Identification cannot be empty!");
            } else if (!validPassword && !validIdentification) {
                presenter.prepareFailView("Password and Identification cannot be empty!");
            } else if (!validUsername) {
                presenter.prepareFailView("Username cannot be empty!");
            } else if (!validPassword) {
                presenter.prepareFailView("Password cannot be empty!");
            } else if (!validIdentification) {
                presenter.prepareFailView("Identification cannot be empty!");
            } else {
                // Create new user
                UserAccount newUser = accountFactory.createUserAccount(signupInputData.getUsername(),
                        signupInputData.getPassword(), signupInputData.getIdentification());
                userDataAccessObject.save(newUser);  // Save this user

                // Prepare output to presenter
                SignupOutputData signupOutputData = new StandardSignupOutputData(newUser.getUsername(), false);
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
        return username != null && !username.isEmpty();
    }

    /**
     * Checks if the provided password is valid (not null or empty).
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    private boolean checkPassword(String password) {
        return password != null && !password.isEmpty();
    }

    /**
     * Checks if the provided identification is valid (not null or empty).
     *
     * @param id the identification to check
     * @return true if the identification is valid, false otherwise
     */
    private boolean checkIdentification(String id) {
        return id != null && !id.isEmpty();
    }
}

