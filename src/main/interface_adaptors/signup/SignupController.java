package interface_adaptors.signup;

import use_case.signup.*;

/**
 * The SignupController class is responsible for handling user interactions related to the signup process.
 * It communicates with the use case interactor to execute the signup process.
 *
 // * This controller uses both a general signup interactor and a shared account signup interactor
 // * to handle different signup scenarios.
 */
public abstract class SignupController<
        InputBoundary extends SignupInputBoundary,
        Interactor extends SignupInteractor> {
    protected final InputBoundary signupInteractor;  // General interactor for standard signup

    /**
     * Constructs a UserAccountSignupController object with the specified use case interactors.
     *
     * @param signupInteractor     the use case interactor for standard user signup
     */
    public SignupController(Interactor signupInteractor) {
        this.signupInteractor = (InputBoundary) signupInteractor;
    }
}

