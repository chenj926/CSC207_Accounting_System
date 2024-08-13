package interface_adaptors.signup.shared_account;

import interface_adaptors.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.shared_account.SharedAccountSignupOutputData;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SharedAccountSignupPresenterTest {

    private SharedAccountSignupPresenter presenter;
    private ViewManagerModel viewManagerModel;
    private SharedAccountSignupViewModel sharedAccountSignupViewModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel(); // Use a real instance instead of a mock
        sharedAccountSignupViewModel = new SharedAccountSignupViewModel();
        presenter = new SharedAccountSignupPresenter(viewManagerModel, sharedAccountSignupViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        SharedAccountSignupOutputData outputData = new SharedAccountSignupOutputData("sharedAccount123", Set.of("user1","user2"));

        presenter.prepareSuccessView(outputData);

        SharedAccountSignupState state = sharedAccountSignupViewModel.getState();
        assertEquals("Shared account signed up successfully.", state.getSuccessMsg());

        assertEquals("home page", viewManagerModel.getActiveViewName());
    }
}
