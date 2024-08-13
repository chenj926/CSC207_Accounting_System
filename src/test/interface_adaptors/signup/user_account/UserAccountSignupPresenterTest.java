package interface_adaptors.signup.user_account;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.user_account.UserAccountSignupOutputData;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountSignupPresenterTest {

    private UserAccountSignupPresenter presenter;
    private ViewManagerModel viewManagerModel;
    private UserAccountSignupViewModel userAccountSignupViewModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel(); // Use a real instance instead of a mock
        userAccountSignupViewModel = new UserAccountSignupViewModel();
        presenter = new UserAccountSignupPresenter(viewManagerModel, userAccountSignupViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        UserAccountSignupOutputData outputData = new UserAccountSignupOutputData("user123", false);

        presenter.prepareSuccessView(outputData);

        UserAccountSignupState state = userAccountSignupViewModel.getState();
        assertEquals("User account signed up successfully.", state.getSuccessMsg());

        assertEquals("home page", viewManagerModel.getActiveViewName());
    }

    @Test
    void testPrepareSuccessView_FailureCase() {
        UserAccountSignupOutputData outputData = new UserAccountSignupOutputData("user123", true);

        presenter.prepareSuccessView(outputData);

        UserAccountSignupState state = userAccountSignupViewModel.getState();
        assertNull(state.getSuccessMsg());

        assertEquals("home page", viewManagerModel.getActiveViewName());
    }
}

